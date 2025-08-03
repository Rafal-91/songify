package com.songify.domain.crud.song;


import org.springframework.data.repository.Repository;

import java.util.Set;

interface ArtistRepository extends Repository<Artist, Long> {
    Artist save(Artist artist);

    Set<Artist> findAll();
}
