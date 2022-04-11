package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);

    Author getByName(String name);

    boolean checkByName(String name);
}
