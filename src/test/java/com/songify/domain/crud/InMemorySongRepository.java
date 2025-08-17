package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class InMemorySongRepository implements SongRepository{

    @Override
    public int delteByIdIn(final Set<Long> ids) {
        return 0;
    }

    @Override
    public List<Song> findAll(final Pageable pageable) {
        return List.of();
    }

    @Override
    public Optional<Song> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public void updateById(final Long id, final Song newSong) {

    }

    @Override
    public Song save(final Song song) {
        return null;
    }

    @Override
    public boolean existsById(final Long id) {
        return false;
    }
}
