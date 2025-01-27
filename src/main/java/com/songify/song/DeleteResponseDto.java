package com.songify.song;

import org.springframework.http.HttpStatus;

public record DeleteResponseDto(String message, HttpStatus httpStatus) {
}
