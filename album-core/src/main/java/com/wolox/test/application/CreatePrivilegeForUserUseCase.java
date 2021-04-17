package com.wolox.test.application;

import com.wolox.test.application.port.input.CreatePrivilegeForUserCommand;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.application.port.output.AlbumRepository;
import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.Album;
import com.wolox.test.domain.AlbumPrivilege;
import com.wolox.test.domain.PrivilegeEnum;
import com.wolox.test.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CreatePrivilegeForUserUseCase implements CreatePrivilegeForUserCommand {

    private final AlbumRepository albumRepository;

    private final AlbumGateway albumGateway;

    private final UserGateway userGateway;

    public CreatePrivilegeForUserUseCase(AlbumRepository albumRepository,
                                         AlbumGateway albumGateway,
                                         UserGateway userGateway) {
        this.albumRepository = albumRepository;
        this.albumGateway = albumGateway;
        this.userGateway = userGateway;
    }

    @Transactional
    @Override
    public void execute(Long userId, Long albumId, Set<PrivilegeEnum> privileges) {
        albumRepository.deleteByUserIdAndAlbumId(userId, albumId);
        User user = userGateway.findById(userId);
        Album album = albumGateway.findById(albumId);
        privileges.forEach(privilege ->
                albumRepository.save(AlbumPrivilege.builder()
                        .album(album)
                        .user(user)
                        .privilege(privilege)
                        .build())
        );

    }
}
