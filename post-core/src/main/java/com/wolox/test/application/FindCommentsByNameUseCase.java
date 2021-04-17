package com.wolox.test.application;

import com.wolox.test.application.port.input.FindCommentsByNameQuery;
import com.wolox.test.application.port.output.CommentGateway;
import com.wolox.test.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCommentsByNameUseCase implements FindCommentsByNameQuery {

    private final CommentGateway commentGateway;

    public FindCommentsByNameUseCase(CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    @Override
    public List<Comment> execute(String name) {
        return commentGateway.findByName(name);
    }

}
