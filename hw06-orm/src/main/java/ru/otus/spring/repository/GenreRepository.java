package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

public interface GenreRepository {

    Genre insert(Genre genre);

    Genre getById(long id);

    boolean checkByName(String name);

    Genre getByName(String name);
}
