package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

public interface GenreRepository {

    void insert(Genre genre);

    Genre getById(long id);

    boolean checkByName(String genreName);

    Genre getByName(String genreName);
}
