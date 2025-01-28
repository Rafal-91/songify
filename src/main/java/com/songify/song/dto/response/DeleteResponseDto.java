package com.songify.song.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteResponseDto(String message, HttpStatus httpStatus) {
}
