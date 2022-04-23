package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    Book insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    void addNewBook();

    long getCount();

    List<Book> getAllBooksByAuthorId(long id);
}
