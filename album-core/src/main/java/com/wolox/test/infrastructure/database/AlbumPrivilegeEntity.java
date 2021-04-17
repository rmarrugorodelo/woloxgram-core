package com.wolox.test.infrastructure.database;

import com.wolox.test.domain.PrivilegeEnum;
import com.wolox.test.infrastructure.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums_privileges")
public class AlbumPrivilegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_albums_privileges")
    @SequenceGenerator(sequenceName = "gall_seq_albums_privileges", name = "seq_albums_privileges")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private PrivilegeEnum privilege;


}
