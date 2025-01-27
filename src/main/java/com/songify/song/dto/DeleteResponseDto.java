package com.songify.song.dto;

import org.springframework.http.HttpStatus;

public record DeleteResponseDto(String message, HttpStatus httpStatus) {
}
