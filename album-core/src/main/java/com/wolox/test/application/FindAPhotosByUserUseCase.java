package com.wolox.test.application;

import com.wolox.test.application.port.input.FindPhotosByUserQuery;
import com.wolox.test.application.port.output.PhotoGateway;
import com.wolox.test.domain.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAPhotosByUserUseCase implements FindPhotosByUserQuery {

    private final PhotoGateway photoGateway;

    public FindAPhotosByUserUseCase(PhotoGateway photoGateway) {
        this.photoGateway = photoGateway;
    }

    @Override
    public List<Photo> execute(Long userId) {
        return photoGateway.findByUserId(userId);
    }

}
