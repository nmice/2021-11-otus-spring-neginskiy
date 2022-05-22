package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

public interface GenreService {

    Genre save(Genre genre);

    Genre getById(long id);

    Genre findByName(String name);
}
