package com.songify.domain.crud.song;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
class Album extends BaseEntity {

    @Id
    @GeneratedValue(generator = "album_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "album_id_seq",
            sequenceName = "album_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String title;

    private Instant releaseDate;

    @OneToMany
    @JoinColumn(name = "album_id")
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artist = new HashSet<>();

    void addSongToAlbum(final Song song){
        songs.add(song);
    }
}
