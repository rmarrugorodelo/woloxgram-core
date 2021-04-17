package com.wolox.test.application;

import com.wolox.test.application.port.input.FindCommentsByUserQuery;
import com.wolox.test.application.port.output.CommentGateway;
import com.wolox.test.application.port.output.PostGateway;
import com.wolox.test.domain.Comment;
import com.wolox.test.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindCommentsByUserUseCase implements FindCommentsByUserQuery {

    private final CommentGateway commentGateway;

    private final PostGateway postGateway;

    public FindCommentsByUserUseCase(CommentGateway commentGateway,
                                     PostGateway postGateway) {
        this.commentGateway = commentGateway;
        this.postGateway = postGateway;
    }

    @Override
    public List<Comment> execute(Long userId) {
        List<Post> posts = postGateway.findByUserId(userId);
        return commentGateway.findAll()
                .parallelStream()
                .filter(comment -> posts
                        .parallelStream()
                        .anyMatch(post ->
                                post.getId().compareTo(comment.getPost().getId()) == 0
                        )
                ).collect(Collectors.toList());
    }

}
