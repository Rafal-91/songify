package com.songify.infrastructure.crud.artist;

import com.songify.domain.crud.song.SongifyCrudFacade;
import com.songify.domain.crud.song.dto.ArtistDto;
import com.songify.domain.crud.song.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/artist")
class ArtistRestController {
    private final SongifyCrudFacade  songifyCrudFacade;

    @PostMapping
    ResponseEntity<ArtistDto> postArtist(@RequestBody ArtistRequestDto artistRequestDto) {
        ArtistDto artistDto = songifyCrudFacade.addArtist(artistRequestDto);
        return ResponseEntity.ok(artistDto);
    }
}
