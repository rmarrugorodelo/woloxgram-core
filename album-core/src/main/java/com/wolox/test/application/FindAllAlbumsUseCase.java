package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAllAlbumsQuery;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.domain.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllAlbumsUseCase implements FindAllAlbumsQuery {

    private final AlbumGateway albumGateway;

    public FindAllAlbumsUseCase(AlbumGateway albumGateway) {
        this.albumGateway = albumGateway;
    }

    @Override
    public List<Album> execute() {
        return albumGateway.findAll();
    }

}
