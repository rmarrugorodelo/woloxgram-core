package com.wolox.test.application.port.input;

import com.wolox.test.domain.Photo;

import java.util.List;

public interface FindPhotosByUserQuery {

    List<Photo> execute(Long userId);

}
