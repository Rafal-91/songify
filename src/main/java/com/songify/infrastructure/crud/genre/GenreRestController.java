package com.songify.infrastructure.crud.genre;

import com.songify.domain.crud.song.SongifyCrudFacade;
import com.songify.domain.crud.song.dto.GenreDto;
import com.songify.domain.crud.song.dto.GenreRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/genres")
class GenreRestController {
    private final SongifyCrudFacade songifyCrudFacade;

    @PostMapping
    ResponseEntity<GenreDto> postGenre(@RequestBody GenreRequestDto genreRequestDto) {
        GenreDto genreDto = songifyCrudFacade.addGenre(genreRequestDto);
        return ResponseEntity.ok(genreDto);
    }
}
