package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepositoryJpa;
import ru.otus.spring.repository.CommentaryRepositoryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repository для работы с книгами должен ")
@DataJpaTest
@Import({BookRepositoryJpa.class, CommentaryRepositoryJpa.class})
class BookRepositoryJpaTest {

    private static final int EXPECTED_BOOK_COUNT = 3;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long FIRST_BOOK_ID = 1;
    private static final String NEW_BOOK_TITLE = "The Greatest Book in the World";
    private static final String FIRST_BOOK_NAME = "Don Quixote";

    @Autowired
    private BookRepositoryJpa bookRepository;

    @Autowired
    private CommentaryRepositoryJpa commentaryRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("корректно добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(4L, "New book title", new Author(1L, "Miguel De Cervantes"), new Genre(2L, "Programming"));
        bookRepository.save(book);
        Book actualBook = bookRepository.findById(4L).get();
        Assertions.assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("верно считать количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookRepository.getCount()).isEqualTo(3);
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
        List<Book> actualBooks = bookRepository.findAll();
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
        Assertions.assertThat(bookRepository.getCount()).isEqualTo(3);
        bookRepository.deleteById(1L);
        Assertions.assertThat(bookRepository.getCount()).isEqualTo(2);
    }

/*    @DisplayName("корректно добавлять книгу в бд")
    @Test
    void shouldInsertBook() {
        Author author = new Author(1L, "Ray Bradbury");
        Genre genre = new Genre(1L, "451 Fahrenheit");
        var book = new Book(NEW_BOOK_TITLE, author, genre);
        book = bookRepository.save(book);

        assertThat(book.getId()).isGreaterThan(0);

        Book actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getTitle().equals(""))
                .matches(b -> b.getAuthor() != null)
                .matches(b -> b.getGenre() != null);
    }

    @DisplayName("корректно загружать информацию о нужной книге по её Id")
    @Test
    void shouldFindBookById() {
        Optional<Book> optionalActualBook = bookRepository.findById(FIRST_BOOK_ID);
        Book expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @DisplayName("корректно загружать список всех книг с информацией об авторе и жанре")
    @Test
    void shouldReturnCorrectBookListWithGenreAndAuthor() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(book -> !book.getTitle().equals(""))
                .allMatch(book -> book.getGenre() != null)
                .allMatch(book -> book.getAuthor() != null);
    }

    @DisplayName("корректно загружать информацию о нужной книге по ее имени")
    @Test
    void shouldFindBookByName() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName("корректно изменять наименование нужной книге по ее Id")
    @Test
    void shouldUpdateBookNameById() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldName = firstBook.getTitle();
        em.clear();

        bookRepository.updateNameById(FIRST_BOOK_ID, NEW_BOOK_TITLE);
        Book updatedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedBook.getTitle()).isNotEqualTo(oldName).isEqualTo(NEW_BOOK_TITLE);
    }

    @DisplayName("корректно удалять нужную книгу по ее Id")
    @Test
    void shouldDeleteBookNameById() {
        em.clear();
        commentaryRepository.deleteByBookId(FIRST_BOOK_ID);
        bookRepository.deleteById(FIRST_BOOK_ID);
        Book deletedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(deletedBook).isNull();
    }

    @DisplayName("верно считать количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookRepository.getCount()).isEqualTo(3);
    }*/
}