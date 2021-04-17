package com.wolox.test.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

public interface AlbumJpaRepository extends CrudRepository<AlbumEntity, Long> {

}
