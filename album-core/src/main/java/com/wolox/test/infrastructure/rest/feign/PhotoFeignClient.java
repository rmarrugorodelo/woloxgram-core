package com.wolox.test.infrastructure.rest.feign;

import com.wolox.test.infrastructure.rest.feign.response.PhotoRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${feign.jsonplaceholder.photos}", url = "${feign.jsonplaceholder.url}")
public interface PhotoFeignClient {

    @GetMapping(value = "${feign.jsonplaceholder.photos}", produces = APPLICATION_JSON_VALUE)
    List<PhotoRestResponse> findAll();

}
