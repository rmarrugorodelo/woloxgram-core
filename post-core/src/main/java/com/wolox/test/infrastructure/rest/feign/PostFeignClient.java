package com.wolox.test.infrastructure.rest.feign;

import com.wolox.test.infrastructure.rest.feign.request.UserIdParam;
import com.wolox.test.infrastructure.rest.feign.response.PostRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${feign.jsonplaceholder.posts}", url = "${feign.jsonplaceholder.url}")
public interface PostFeignClient {

    @GetMapping(value = "${feign.jsonplaceholder.posts}", produces = APPLICATION_JSON_VALUE)
    List<PostRestResponse> findAll();

    @GetMapping(value = "${feign.jsonplaceholder.posts}", produces = APPLICATION_JSON_VALUE)
    List<PostRestResponse> findByUser(@SpringQueryMap UserIdParam userIdParam);
}
