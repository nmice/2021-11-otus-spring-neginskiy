package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository {

    long getCount();

    Book insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
