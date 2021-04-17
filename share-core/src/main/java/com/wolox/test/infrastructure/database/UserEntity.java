package com.wolox.test.infrastructure.database;

import com.wolox.test.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;

    public static UserEntity of(User user) {
        return UserEntity
                .builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    public User toDomain() {
        return User
                .builder()
                .id(id)
                .name(name)
                .username(username)
                .phone(phone)
                .email(email)
                .build();
    }


}
