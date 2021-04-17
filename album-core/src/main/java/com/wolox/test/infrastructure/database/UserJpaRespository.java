package com.wolox.test.infrastructure.database;

import com.wolox.test.infrastructure.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRespository extends CrudRepository<UserEntity, Long> {
}
