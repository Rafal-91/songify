package com.songify.infrastructure.crud.album;

import com.songify.domain.crud.song.SongifyCrudFacade;
import com.songify.domain.crud.song.dto.AlbumDto;
import com.songify.domain.crud.song.dto.AlbumRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/album")
class AlbumRestController {

    private final SongifyCrudFacade songifyCrudFacade;

    @PostMapping
    ResponseEntity<AlbumDto> postAlbum(@RequestBody AlbumRequestDto albumRequestDto) {
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(albumRequestDto);
        return ResponseEntity.ok(albumDto);
    }

}