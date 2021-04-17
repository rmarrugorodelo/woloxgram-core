package com.wolox.test.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

public interface UserJpaRespository extends CrudRepository<UserEntity, Long> {
}
