package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAllCommentsQuery;
import com.wolox.test.application.port.output.CommentGateway;
import com.wolox.test.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCommentsUseCase implements FindAllCommentsQuery {

    private final CommentGateway commentGateway;

    public FindAllCommentsUseCase(CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    @Override
    public List<Comment> execute() {
        return commentGateway.findAll();
    }

}
