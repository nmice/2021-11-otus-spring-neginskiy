package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Dao для работы с книгами")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;

    private static final String NEW_BOOK_TITLE = "new book";
    private static final long NEW_BOOK_ID = 4;
    private static final long GET_BOOK_ID = 1;
    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("возращать правильное количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookDao.getCount()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, new Author(1L, "Miguel De Cervantes"), new Genre(2L, "Programming"));
        bookDao.insert(book);
        Book actualBook = bookDao.getById(NEW_BOOK_ID);
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("получать нужную книгу по Id")
    @Test
    void shouldReturnCorrectBookById() {
        Book book = new Book(GET_BOOK_ID, "Don Quixote", new Author(1L, "Miguel De Cervantes"), new Genre(1L, "Novel"));
        Book actualBook = bookDao.getById(GET_BOOK_ID);
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("получить все книги")
    @Test
    void shouldReturnCorrectBookList() {
        Book book1 = new Book(1L, "Don Quixote", new Author(1L, "Miguel De Cervantes"), new Genre(1L, "Novel"));
        Book book2 = new Book(2L, "Java Headfirst", new Author(2L, "Kathy Sierra"), new Genre(2L, "Programming"));
        Book book3 = new Book(3L, "After three it`s too late", new Author(3L, "Masaru Ibuka"), new Genre(3L, "Psychology"));
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> actualBooks = bookDao.getAll();
        Assertions.assertThat(actualBooks).isEqualTo(books);
    }
}