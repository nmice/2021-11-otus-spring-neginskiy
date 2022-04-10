package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.InputOutputService;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService bookService;
    private final InputOutputService ioService;

    @Autowired
    public BookCommands(BookService bookService, InputOutputService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Show all books", key = {"book list", "all"})
    public void allBooks() {
        List<Book> books = bookService.getAll();
        books.forEach(book -> ioService.output(book.toString()));
    }

    @ShellMethod(value = "Add book to library", key = {"add"})
    public void addBook() {
        Book book = bookService.getNewBook();
        bookService.insert(book);
    }

    @ShellMethod(value = "Get book by Id", key = {"getById", "gbi"})
    public void getBookById() {
        long id = ioService.inputInt();
        ioService.output(bookService.getById(id).toString());
    }

    @ShellMethod(value = "Delete book by Id", key = {"deleteById", "dbi"})
    public void deleteBookById() {
        long id = ioService.inputInt();
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Count of all books", key = "count")
    public void bookCount() {
        ioService.output(bookService.getCount());
    }
}