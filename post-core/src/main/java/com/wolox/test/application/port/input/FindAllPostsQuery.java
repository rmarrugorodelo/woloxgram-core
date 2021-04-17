package com.wolox.test.application.port.input;

import com.wolox.test.domain.Post;

import java.util.List;

public interface FindAllPostsQuery {

    List<Post> execute();

}
