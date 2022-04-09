package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(Long id);

    Author getByName(String name);

    boolean checkByName(String name);
}
