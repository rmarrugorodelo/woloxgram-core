package com.wolox.test.application.port.output;

import com.wolox.test.domain.AlbumPrivilege;

public interface AlbumRepository {

    void save(AlbumPrivilege albumPrivilege);

    void deleteByUserIdAndAlbumId(Long userId, Long albumId);

}
