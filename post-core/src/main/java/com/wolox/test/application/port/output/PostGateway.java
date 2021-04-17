package com.wolox.test.application.port.output;

import com.wolox.test.domain.Post;

import java.util.List;

public interface PostGateway {

    List<Post> findAll();

    List<Post> findByUserId(Long userId);

}
