package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);

    boolean checkByName(String genreName);

    Genre getByName(String genreName);
}
