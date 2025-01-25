package com.songify;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class SongsController {

    Map<Integer, String> database = new HashMap<>(Map.of(
            1, "shawnmendes song1",
            2, "ariana grande song2",
            3, "ariana grande asdfadsfadsf",
            4, "ariana grande sdfadsfdsfds",
            5, "ariana grande sosdfdsfdsfng2"
    ));

    @GetMapping("/songs")
    public ResponseEntity<SongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit){
        if (limit != null) {
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongsResponseDto response = new SongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        SongsResponseDto response = new SongsResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId){
        log.info(requestId);
        String song = database.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping("songs")
    public ResponseEntity<SingleSongResponseDto> postSong(@RequestBody @Valid SongRequestDto request){
        String songName = request.songName();
        log.info("added new song: " + songName);
        database.put(database.size() + 1, songName);
        return ResponseEntity.ok(new SingleSongResponseDto(songName));
    }
}
