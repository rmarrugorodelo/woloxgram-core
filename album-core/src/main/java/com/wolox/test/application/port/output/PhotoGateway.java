package com.wolox.test.application.port.output;

import com.wolox.test.domain.Photo;

import java.util.List;

public interface PhotoGateway {

    List<Photo> findAll();
    
}
