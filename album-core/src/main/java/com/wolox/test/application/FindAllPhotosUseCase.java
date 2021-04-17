package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAllPhotosQuery;
import com.wolox.test.application.port.output.PhotoGateway;
import com.wolox.test.domain.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPhotosUseCase implements FindAllPhotosQuery {

    private final PhotoGateway photoGateway;

    public FindAllPhotosUseCase(PhotoGateway photoGateway) {
        this.photoGateway = photoGateway;
    }

    @Override
    public List<Photo> execute() {
        return photoGateway.findAll();
    }

}
