package com.wolox.test.infrastructure.database;

import com.wolox.test.application.port.output.AlbumRepository;
import com.wolox.test.domain.AlbumPrivilege;
import com.wolox.test.domain.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumJpaAdapter implements AlbumRepository {

    private final AlbumPrivilegeJpaRepository albumPrivilegeJpaRepository;

    private final UserJpaRespository userJpaRespository;

    private final AlbumJpaRepository albumJpaRepository;

    public AlbumJpaAdapter(AlbumPrivilegeJpaRepository albumPrivilegeJpaRepository,
                           UserJpaRespository userJpaRespository,
                           AlbumJpaRepository albumJpaRepository) {
        this.albumPrivilegeJpaRepository = albumPrivilegeJpaRepository;
        this.userJpaRespository = userJpaRespository;
        this.albumJpaRepository = albumJpaRepository;
    }

    @Override
    public void save(AlbumPrivilege albumPrivilege) {
        AlbumEntity album = albumJpaRepository.save(AlbumEntity.of(albumPrivilege.getAlbum()));
        UserEntity user = userJpaRespository.save(UserEntity.of(albumPrivilege.getUser()));
        albumPrivilegeJpaRepository.save(AlbumPrivilegeEntity
                .builder()
                .album(album)
                .user(user)
                .privilege(albumPrivilege.getPrivilege())
                .build());
    }

    @Override
    public void deleteByUserIdAndAlbumId(Long userId, Long albumId) {
        albumPrivilegeJpaRepository.deleteByUserIdAndAlbumId(userId, albumId);
    }

    @Override
    public List<AlbumPrivilege> findByAlbumAndPrivilege(Long albumId, Privilege privilege) {
        return albumPrivilegeJpaRepository.findByAlbumIdAndPrivilege(albumId, privilege)
                .parallelStream()
                .map(AlbumPrivilegeEntity::toDomain)
                .collect(Collectors.toList());
    }

}
