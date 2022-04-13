package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

public interface AuthorRepository {

    void insert(Author author);

    Author getById(long id);

    Author getByName(String name);

    boolean checkByName(String name);
}
