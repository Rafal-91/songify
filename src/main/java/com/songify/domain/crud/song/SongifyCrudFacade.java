package com.songify.domain.crud.song;

import com.songify.domain.crud.song.dto.AlbumDto;
import com.songify.domain.crud.song.dto.AlbumRequestDto;
import com.songify.domain.crud.song.dto.ArtistDto;
import com.songify.domain.crud.song.dto.ArtistRequestDto;
import com.songify.domain.crud.song.dto.GenreDto;
import com.songify.domain.crud.song.dto.GenreRequestDto;
import com.songify.domain.crud.song.dto.SongDto;
import com.songify.domain.crud.song.dto.SongRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SongifyCrudFacade {

    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;
    private final SongAdder songAdder;
    private final ArtistAdder artistAdder;
    private final GenreAdder genreAdder;
    private final AlbumAdder albumAdder;

    public ArtistDto addArtist(ArtistRequestDto artistRequestDto){
        return artistAdder.addArtist(artistRequestDto.name());
    }

    public GenreDto addGenre(GenreRequestDto genreRequestDto){
        return genreAdder.addGenre(genreRequestDto.name());
    }

    public AlbumDto addAlbumWithSong(final AlbumRequestDto albumRequestDto) {
        return albumAdder.addAlbum(albumRequestDto.songId(), albumRequestDto.title(), albumRequestDto.releaseDate());
    }

    public SongDto addSong(final SongRequestDto songRequestDto) {
        return songAdder.addSong(songRequestDto);
    }

    public List<SongDto> findAll(Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .name(song.getName())
                        .build())
                .toList();
    }

    public void updateById(Long id, SongDto newSongDto) {
        songRetriever.existsById(id);
        // some domain validator
        Song songValidatedAndReadyToUpdate = new Song(newSongDto.name());
        // some domain validator ended checking
        songUpdater.updateById(id, songValidatedAndReadyToUpdate);
    }

    public SongDto updatePartiallyById(Long id, SongDto songFromRequest) {
        songRetriever.existsById(id);
        Song songFromDatabase = songRetriever.findSongById(id);
        Song toSave = new Song();
        if (songFromRequest.name() != null) {
            toSave.setName(songFromRequest.name());
        } else {
            toSave.setName(songFromDatabase.getName());
        }
//        todo
//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
        songUpdater.updateById(id, toSave);
        return SongDto.builder()
                .id(toSave.getId())
                .name(toSave.getName())
                .build();

    }

    public void deleteById(Long id) {
        songRetriever.existsById(id);
        songDeleter.deleteById(id);
    }

    public SongDto findSongDtoById(Long id) {
        Song song = songRetriever.findSongById(id);
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .build();
    }


}
