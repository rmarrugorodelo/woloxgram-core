package com.wolox.test.application.port.input;

import com.wolox.test.domain.Album;

import java.util.List;

public interface FindAlbumsByUserQuery {

    List<Album> execute(Long userId);

}
