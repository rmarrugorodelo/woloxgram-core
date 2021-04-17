package com.wolox.test.application.port.input;

import com.wolox.test.domain.Photo;

import java.util.List;

public interface FindAllPhotosQuery {

    List<Photo> execute();

}
