package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAlbumsByUserQuery;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.domain.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUsersByAlbumAndPrivilegeUseCase implements FindAlbumsByUserQuery {

    private final AlbumGateway albumGateway;

    public FindUsersByAlbumAndPrivilegeUseCase(AlbumGateway albumGateway) {
        this.albumGateway = albumGateway;
    }

    @Override
    public List<Album> execute(Long userId) {
        return albumGateway.findByUserId(userId);
    }

}
