package com.wolox.test.application;

import com.wolox.test.application.port.input.FindUsersByAlbumAndPrivilegeQuery;
import com.wolox.test.application.port.output.AlbumRepository;
import com.wolox.test.domain.AlbumPrivilege;
import com.wolox.test.domain.Privilege;
import com.wolox.test.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAlbumsByUserUseCase implements FindUsersByAlbumAndPrivilegeQuery {

    private final AlbumRepository albumRepository;

    public FindAlbumsByUserUseCase(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @Override
    public List<User> execute(Long albumId, Privilege privilege) {
        return albumRepository.findByAlbumAndPrivilege(albumId, privilege)
                .parallelStream()
                .map(AlbumPrivilege::getUser)
                .collect(Collectors.toList());
    }
}
