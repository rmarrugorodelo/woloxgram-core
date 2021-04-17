package com.wolox.test.infrastructure.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String username;

    private String password;

}
