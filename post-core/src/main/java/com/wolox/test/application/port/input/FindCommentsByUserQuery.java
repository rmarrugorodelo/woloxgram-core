package com.wolox.test.application.port.input;

import com.wolox.test.domain.Comment;

import java.util.List;

public interface FindCommentsByUserQuery {

    List<Comment> execute(Long userId);

}
