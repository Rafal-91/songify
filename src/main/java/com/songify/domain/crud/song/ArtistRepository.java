package com.songify.domain.crud.song;


import org.springframework.data.repository.Repository;

interface ArtistRepository extends Repository<Artist, Long> {
    Artist save(Artist artist);
}
