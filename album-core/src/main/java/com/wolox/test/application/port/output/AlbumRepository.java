package com.wolox.test.application.port.output;

import com.wolox.test.domain.AlbumPrivilege;
import com.wolox.test.domain.Privilege;

import java.util.List;

public interface AlbumRepository {

    void save(AlbumPrivilege albumPrivilege);

    void deleteByUserIdAndAlbumId(Long userId, Long albumId);

    List<AlbumPrivilege> findByAlbumAndPrivilege(Long albumId, Privilege privilege);

}
