package com.wolox.test.application;

import com.wolox.test.application.port.input.AssignPrivilegeForUserCommand;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.application.port.output.AlbumRepository;
import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.Album;
import com.wolox.test.domain.AlbumPrivilege;
import com.wolox.test.domain.Privilege;
import com.wolox.test.domain.User;
import com.wolox.test.domain.exception.UserIsOwnerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AssignPrivilegeForUserUseCase implements AssignPrivilegeForUserCommand {

    private static final String USER_IS_OWNER_MESSAGE = "El usuario ingresado es dueño del album";

    private final AlbumRepository albumRepository;

    private final AlbumGateway albumGateway;

    private final UserGateway userGateway;

    public AssignPrivilegeForUserUseCase(AlbumRepository albumRepository,
                                         AlbumGateway albumGateway,
                                         UserGateway userGateway) {
        this.albumRepository = albumRepository;
        this.albumGateway = albumGateway;
        this.userGateway = userGateway;
    }

    @Transactional
    @Override
    public void execute(Long userId, Long albumId, Set<Privilege> privileges) {
        Album album = albumGateway.findById(albumId);
        validateIfUserIsOwner(album, userId);
        User user = userGateway.findById(userId);
        albumRepository.deleteByUserIdAndAlbumId(userId, albumId);
        privileges.forEach(privilege ->
                albumRepository.save(AlbumPrivilege.builder()
                        .album(album)
                        .user(user)
                        .privilege(privilege)
                        .build())
        );
    }

    private void validateIfUserIsOwner(Album album, Long userId) {
        if(album.getUser().getId().equals(userId)) {
            throw new UserIsOwnerException(USER_IS_OWNER_MESSAGE);
        }
    }
}
