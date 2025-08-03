package com.songify.domain.crud.song;


import org.springframework.data.repository.Repository;

interface AlbumRepository extends Repository<Album, Long> {
    Album save(Album album);
}
