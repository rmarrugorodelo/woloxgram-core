package com.wolox.test.infrastructure.database;


import com.wolox.test.domain.Privilege;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumPrivilegeJpaRepository extends CrudRepository<AlbumPrivilegeEntity, Long> {

    void deleteByUserIdAndAlbumId(Long userId, Long albumId);

    List<AlbumPrivilegeEntity> findByAlbumIdAndPrivilege(Long albumId, Privilege privilege);

}
