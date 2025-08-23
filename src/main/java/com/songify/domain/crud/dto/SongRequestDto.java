package com.songify.domain.crud.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SongRequestDto(
        String name,
        Long duration,
        Instant releaseDate,
        SongLanguageDto language
) {
}
