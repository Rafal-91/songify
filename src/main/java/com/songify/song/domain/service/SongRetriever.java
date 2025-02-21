package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class SongRetriever {

    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        log.info("Retrieving all songs");
        return songRepository.findAll();
    }

    public List<Song> findAllLimitedBy(Integer limit) {
        return findAll()
                .stream()
                .limit(limit)
                .toList();
    }

    public Optional<Song> findSongById(Long id) {
        return songRepository.findById(id);
    }

    public void existsById(Long id) {
        findSongById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

}
