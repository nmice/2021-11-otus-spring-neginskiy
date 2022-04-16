package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final InputOutputService ioService;


    public BookServiceImpl(BookRepository bookRepository, GenreService genreService, AuthorService authorService, InputOutputService ioService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.authorService = authorService;
        this.ioService = ioService;
    }

    @Override
    public long getCount() {
        return bookRepository.getCount();
    }

    @Override
    public void insert(Book book) {
        bookRepository.insert(book);
    }

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getNewBook() {
        ioService.output("Enter book title");
        String title = ioService.input();
        ioService.output("Enter Genre");
        String genreName = ioService.input();
        ioService.output("Enter Author");
        String authorName = ioService.input();
        Genre genre = genreService.getGenre(genreName);
        Author author = authorService.getAuthor(authorName);
        return new Book(null, title, author, genre, null);
    }
}
