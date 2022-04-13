package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final InputOutputService ioService;


    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService, InputOutputService ioService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
        this.ioService = ioService;
    }

    @Override
    public int getCount() {
        return bookDao.getCount();
    }

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
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
        return new Book(title, author, genre);
    }
}
