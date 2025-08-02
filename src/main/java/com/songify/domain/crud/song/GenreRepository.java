package com.songify.domain.crud.song;

import org.springframework.data.repository.Repository;

interface GenreRepository extends Repository<Genre, Integer> {
    Genre save(Genre genre);
}
