package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreService {

    Genre insert(Genre genre);

    Optional<Genre> getById(long id);

    Genre findByName(String name);
}
