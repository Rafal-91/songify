package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Generated
@Getter
@Setter
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String artist;

    @Column
    String album;

    public Song() {

    }

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public Song(Long id, String name, String artist, String album) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

}
