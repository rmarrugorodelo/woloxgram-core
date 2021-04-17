package com.wolox.test.application.port.output;

import com.wolox.test.domain.Comment;

import java.util.List;

public interface CommentGateway {

    List<Comment> findAll();

    List<Comment> findByName(String name);

}
