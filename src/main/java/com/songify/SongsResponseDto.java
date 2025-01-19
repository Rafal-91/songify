package com.songify;

import java.util.List;
import java.util.Map;

public record SongsResponseDto(Map<Integer, String> songs) {
}
