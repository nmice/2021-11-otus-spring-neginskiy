package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository {

    int getCount();

    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
