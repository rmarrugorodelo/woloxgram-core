package com.wolox.test.infrastructure.rest.feign;

import com.wolox.test.infrastructure.rest.feign.response.AlbumRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${feign.jsonplaceholder.albums}", url = "${feign.jsonplaceholder.url}")
public interface AlbumFeignClient {

    @GetMapping(value = "${feign.jsonplaceholder.albums}", produces = APPLICATION_JSON_VALUE)
    List<AlbumRestResponse> findAll();

    @GetMapping(value = "${feign.jsonplaceholder.users-albums}", produces = APPLICATION_JSON_VALUE)
    List<AlbumRestResponse> findByUser(@PathVariable("userId") Long userId);
}
