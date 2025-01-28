package com.songify.song.controller;

import com.songify.song.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.dto.request.SongRequestDto;
import com.songify.song.dto.request.UpdateSongRequestDto;
import com.songify.song.dto.response.*;
import com.songify.song.error.SongNotFoundException;
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
public class SongRestController {

    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("shawn mendes song1", "Shawn Mendes"),
            2, new Song("ariana grande song2", "Ariana Grande"),
            3, new Song("ariana grande song3", "Ariana Grande"),
            4, new Song("ariana grande song4", "Ariana Grande"),
            5, new Song("ariana grande song5", "Ariana Grande")
    ));

    @GetMapping("/songs")
    public ResponseEntity<SongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        if (limit != null) {
            Map<Integer, Song> limitedMap = database.entrySet()
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
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        if (!database.containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = database.get(id);
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping("songs")
    public ResponseEntity<SingleSongResponseDto> postSong(@RequestBody @Valid SongRequestDto request) {
        Song song = new Song(request.songName(), request.artistName());
        log.info("added new song: " + song);
        database.put(database.size() + 1, song);
        return ResponseEntity.ok(new SingleSongResponseDto(song));
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<DeleteResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        if (!database.containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        database.remove(id);
        return ResponseEntity.ok(new DeleteResponseDto("You deleted song with id " + id, HttpStatus.OK));
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id, @RequestBody @Valid UpdateSongRequestDto request) {
        if (!database.containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        String newSongName = request.songName();
        String newArtist = request.artist();
        Song newSong = new Song(newSongName, newArtist);
        Song oldSong = database.put(id, newSong);
        log.info("updated newSong with id: " + id +
                " with oldSongName: " + oldSong.name() + " to newSongName: " + newSong.name() + "old artistName: " +
                oldSong.artist() + " to newArtist " + newSong.artist());
        return ResponseEntity.ok(new UpdateSongResponseDto(newSongName, newArtist));
    }

    @PatchMapping("/songs/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdate(@PathVariable Integer id, @RequestBody PartiallyUpdateSongRequestDto request) {
        if (!database.containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song songFromDatabase = database.get(id);
        Song.SongBuilder builder = Song.builder();
        if(request.songName() != null) {
            builder.name(request.songName());
            log.info("partially updated song name");
        }else{
            builder.name(songFromDatabase.name());
        }
        if(request.artistName() != null) {
            builder.artist(request.artistName());
            log.info("partially updated artist");
        }else{
            builder.name(songFromDatabase.artist());
        }
        Song updatedSong = builder.build();
        database.put(id, updatedSong);
        return ResponseEntity.ok(new PartiallyUpdateSongResponseDto(updatedSong.name(), updatedSong.artist()));
    }
}
