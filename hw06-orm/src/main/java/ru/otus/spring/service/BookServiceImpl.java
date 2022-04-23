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

    public BookServiceImpl(BookRepository bookRepository,
                           GenreService genreService,
                           AuthorService authorService,
                           InputOutputService ioService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.authorService = authorService;
        this.ioService = ioService;
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        bookRepository.updateNameById(id, name);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addNewBook() {
        ioService.output("Enter book title");
        String title = ioService.input();
        ioService.output("Enter Genre");
        String genreName = ioService.input();
        ioService.output("Enter Author");
        String authorName = ioService.input();
        Author author = authorService.getByName(authorName);
        if (author == null) author = new Author(authorName);
        Genre genre = genreService.findByName(genreName);
        if (genre == null) genre = new Genre(genreName);
        Book book = new Book(title, author, genre);
        bookRepository.save(book);
    }

    @Override
    public long getCount() {
        return bookRepository.getCount();
    }

    @Override
    public List<Book> getAllBooksByAuthorId(long id) {
        return bookRepository.findAllBooksByAuthorId(id);
    }
}
