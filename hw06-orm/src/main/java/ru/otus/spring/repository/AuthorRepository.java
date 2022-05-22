package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    Author findByName(String name);
}
