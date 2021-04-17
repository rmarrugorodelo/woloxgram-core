package com.wolox.test.infrastructure.database;


import org.springframework.data.repository.CrudRepository;

public interface AlbumPrivilegeJpaRepository extends CrudRepository<AlbumPrivilegeEntity, Long> {

    void deleteByUserIdAndAlbumId(Long userId, Long albumId);

}
