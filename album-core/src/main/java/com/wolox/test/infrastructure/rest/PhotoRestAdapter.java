package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.PhotoGateway;
import com.wolox.test.domain.Photo;
import com.wolox.test.infrastructure.rest.feign.PhotoFeignClient;
import com.wolox.test.infrastructure.rest.feign.response.PhotoRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoRestAdapter implements PhotoGateway {

    private final PhotoFeignClient photoFeignClient;

    public PhotoRestAdapter(PhotoFeignClient photoFeignClient) {
        this.photoFeignClient = photoFeignClient;
    }

    @Override
    public List<Photo> findAll() {
        return photoFeignClient.findAll()
                .parallelStream()
                .map(PhotoRestResponse::toDomain)
                .collect(Collectors.toList());
    }

}
