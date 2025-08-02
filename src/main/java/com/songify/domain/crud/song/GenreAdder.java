package com.songify.domain.crud.song;

import com.songify.domain.crud.song.dto.GenreDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GenreAdder {

    private final GenreRepository genreRepository;

    GenreDto addGenre(final String name) {
        Genre genre = new Genre(name);
        Genre save = genreRepository.save(genre);
        return new GenreDto(save.getId(), save.getName());
    }
}
