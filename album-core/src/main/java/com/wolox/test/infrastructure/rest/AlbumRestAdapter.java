package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.domain.Album;
import com.wolox.test.infrastructure.rest.feign.AlbumFeignClient;
import com.wolox.test.infrastructure.rest.feign.response.AlbumRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumRestAdapter implements AlbumGateway {

    private final AlbumFeignClient albumFeignClient;

    public AlbumRestAdapter(AlbumFeignClient albumFeignClient) {
        this.albumFeignClient = albumFeignClient;
    }

    @Override
    public List<Album> findAll() {
        return albumFeignClient.findAll()
                .parallelStream()
                .map(AlbumRestResponse::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Album> findByUserId(Long userId) {
        return albumFeignClient.findByUser(userId)
                .parallelStream()
                .map(AlbumRestResponse::toDomain)
                .collect(Collectors.toList());
    }

}
