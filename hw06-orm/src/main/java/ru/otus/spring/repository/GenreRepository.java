package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Genre findByName(String name);
}
