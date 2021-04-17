package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAllPostsQuery;
import com.wolox.test.application.port.output.PostGateway;
import com.wolox.test.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPostsUseCase implements FindAllPostsQuery {

    private final PostGateway postGateway;

    public FindAllPostsUseCase(PostGateway postGateway) {
        this.postGateway = postGateway;
    }

    @Override
    public List<Post> execute() {
        return postGateway.findAll();
    }

}
