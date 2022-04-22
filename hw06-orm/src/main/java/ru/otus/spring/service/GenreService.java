package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreService {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);


    Genre findByName(String name);
}
