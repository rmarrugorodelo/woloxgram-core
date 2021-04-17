package com.wolox.test.infrastructure.rest.feign;

import com.wolox.test.infrastructure.rest.feign.request.IdParam;
import com.wolox.test.infrastructure.rest.feign.response.UserRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${feign.jsonplaceholder.users}", url = "${feign.jsonplaceholder.url}")
public interface UserFeignClient {

    @GetMapping(value = "${feign.jsonplaceholder.users}", produces = APPLICATION_JSON_VALUE)
    List<UserRestResponse> findAll();

    @GetMapping(value = "${feign.jsonplaceholder.users}", produces = APPLICATION_JSON_VALUE)
    List<UserRestResponse> findById(@SpringQueryMap IdParam idParam);

}
