package com.wolox.test.infrastructure.database;

import com.wolox.test.domain.Album;
import com.wolox.test.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums")
public class AlbumEntity {

    @Id
    private Long id;

    private String title;

    @Column(name = "user_id")
    private Long userId;

    public static AlbumEntity of(Album album) {
        return AlbumEntity
                .builder()
                .id(album.getId())
                .title(album.getTitle())
                .userId(album.getUser().getId())
                .build();
    }

    public Album toDomain() {
        return Album
                .builder()
                .id(id)
                .user(User
                        .builder()
                        .id(id)
                        .build()
                )
                .title(title)
                .build();
    }

}
