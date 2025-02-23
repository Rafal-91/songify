package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void updateSongById(Long id, Song song) {
        songRetriever.existsSongById(id);
        log.info("updating song by id: {}", id);
        songRepository.updateById(id, song);
    }

    public Song updatePartiallyById(Long id, Song songFromRequest){
        Song songFromDatabase = songRetriever.findSongById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.getName() != null) {
            builder.name(songFromRequest.getName());
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (songFromRequest.getArtist() != null) {
            builder.artist(songFromRequest.getArtist());
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        Song toSave = builder.build();
        updateSongById(id, toSave);
        return toSave;
    }
}

