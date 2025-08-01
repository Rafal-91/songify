package com.songify.infrastructure.apivalidation;

import java.util.List;
import org.springframework.http.HttpStatus;

public record ApiValidationErrorResponseDto(List<String> errors, HttpStatus status) {
}