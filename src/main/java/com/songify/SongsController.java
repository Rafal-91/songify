package com.songify;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SongsController {

    Map<Integer, String> database = new HashMap<>();

    @GetMapping("/songs")
    public ResponseEntity<SongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit){
        database.put(1, "shawnmendes song1");
        database.put(2, "ariana grande song2");
        database.put(3, "ariana grande asdfadsfadsf");
        database.put(4, "ariana grande sdfadsfdsfds");
        database.put(5, "ariana grande sosdfdsfdsfng2");

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
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id){
        String song = database.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }
}
