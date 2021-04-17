package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.domain.Album;
import com.wolox.test.domain.exception.RecordNotFoundException;
import com.wolox.test.infrastructure.rest.feign.AlbumFeignClient;
import com.wolox.test.infrastructure.rest.feign.request.IdParam;
import com.wolox.test.infrastructure.rest.feign.response.AlbumRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumRestAdapter implements AlbumGateway {

    private static final String RECORD_NOT_FOUND = "No se encontró album con el id ingresado";

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

    @Override
    public Album findById(Long id) {
        return albumFeignClient.findById(IdParam
                .builder()
                .id(id)
                .build()
        ) .parallelStream()
                .map(AlbumRestResponse::toDomain)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException(RECORD_NOT_FOUND));
    }

}
