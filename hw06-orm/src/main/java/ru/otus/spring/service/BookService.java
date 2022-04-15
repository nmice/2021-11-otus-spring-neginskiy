package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    long getCount();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    Book getNewBook();
}
