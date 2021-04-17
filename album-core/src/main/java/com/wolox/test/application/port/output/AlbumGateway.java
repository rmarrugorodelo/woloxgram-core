package com.wolox.test.application.port.output;

import com.wolox.test.domain.Album;

import java.util.List;

public interface AlbumGateway {

    List<Album> findAll();

    List<Album> findByUserId(Long userId);
}
