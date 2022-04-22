package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    void addNewBook();

    long getCount();

    List<Book> findAllBooksByAuthorId(long id);

    List<Book> findAllWithCommentaries();

    Map<Book, Long> findAllBooksWithCommentariesCount();
}
