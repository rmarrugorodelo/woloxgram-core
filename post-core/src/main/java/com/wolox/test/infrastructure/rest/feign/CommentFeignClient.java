package com.wolox.test.infrastructure.rest.feign;

import com.wolox.test.infrastructure.rest.feign.request.NameParam;
import com.wolox.test.infrastructure.rest.feign.response.CommentRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${feign.jsonplaceholder.comments}", url = "${feign.jsonplaceholder.url}")
public interface CommentFeignClient {

    @GetMapping(value = "${feign.jsonplaceholder.comments}", produces = APPLICATION_JSON_VALUE)
    List<CommentRestResponse> findAll();

    @GetMapping(value = "${feign.jsonplaceholder.comments}", produces = APPLICATION_JSON_VALUE)
    List<CommentRestResponse> findByName(@SpringQueryMap NameParam nameParam);

}
