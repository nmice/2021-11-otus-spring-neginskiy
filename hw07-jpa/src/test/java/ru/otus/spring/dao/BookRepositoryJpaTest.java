package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;


@DisplayName("Repository для работы с книгами должен ")
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    private final String NEW_BOOK_NAME = "Dom Perignon";

    @DisplayName("корректно добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(4L, "New book title", new Author(1L, "Miguel De Cervantes"), new Genre(2L, "Programming"));
        bookRepository.save(book);
        Book actualBook = em.find(Book.class, 4L);
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("верно считать количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookRepository.count()).isEqualTo(3);
    }

    @DisplayName("получать все книги")
    @Test
    void shouldReturnCorrectBookList() {
        Book book1 = new Book(1L, "Don Quixote", new Author(1L, "Miguel De Cervantes"), new Genre(1L, "Novel"));
        Book book2 = new Book(2L, "Java Headfirst", new Author(2L, "Kathy Sierra"), new Genre(2L, "Programming"));
        Book book3 = new Book(3L, "After three it`s too late", new Author(3L, "Masaru Ibuka"), new Genre(3L, "Psychology"));
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        Iterable<Book> actualBooks = bookRepository.findAll();
        Assertions.assertThat(actualBooks).isEqualTo(books);
    }

    @DisplayName("получать нужную книгу по id")
    @Test
    void shouldReturnCorrectBookById() {
        Book book = new Book(1L, "Don Quixote", new Author(1L, "Miguel De Cervantes"), new Genre(1L, "Novel"));
        Book actualBook = bookRepository.findById(1L).get();
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("удалять нужную книгу из БД")
    @Test
    void shouldCorrectDeleteBookById() {
        Assertions.assertThat(bookRepository.count()).isEqualTo(3);
        bookRepository.deleteById(1L);
        Assertions.assertThat(bookRepository.count()).isEqualTo(2);
    }

    @DisplayName("обновлять имя книги по Id")
    @Test
    void shouldCorrectUpdateBookNameById() {
        bookRepository.updateNameById(1L, NEW_BOOK_NAME);
        Book b = em.find(Book.class, 1L);
        Assertions.assertThat(b.getTitle()).isEqualTo(NEW_BOOK_NAME);
    }
}