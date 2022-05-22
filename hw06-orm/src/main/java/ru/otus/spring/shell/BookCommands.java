package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentaryService;
import ru.otus.spring.service.InputOutputService;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CommentaryService commentaryService;
    private final InputOutputService ioService;

    @Autowired
    public BookCommands(BookService bookService,
                        AuthorService authorService,
                        CommentaryService commentaryService,
                        InputOutputService ioService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.commentaryService = commentaryService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Show all books", key = {"book list", "all"})
    public void showAllBooks() {
        List<Book> books = bookService.getAll();
        books.forEach(ioService::output);
    }

    @ShellMethod(value = "Add book to library", key = {"add"})
    public void addBookToLibrary() {
        ioService.output("Enter book title");
        String title = ioService.input();
        ioService.output("Enter Genre");
        String genreName = ioService.input();
        ioService.output("Enter Author");
        String authorName = ioService.input();
        bookService.addNewBook(title, genreName, authorName);
    }

    @ShellMethod(value = "Get book by Id", key = {"get by id", "get"})
    public void getBookById(@ShellOption long id) {
        ioService.output(bookService.getById(id));
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete by id", "del"})
    public void deleteBookById(@ShellOption long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Count of all books", key = {"count", "c"})
    public void countAllBooks() {
        ioService.output(bookService.getCount());
    }

    @ShellMethod(value = "Update book title by Id", key = {"update title by id", "upd"})
    public void updateBookTitleById() {
        ioService.output("Enter book id to change");
        long id = ioService.inputInt();
        ioService.output("Enter a new book title");
        String name = ioService.input();
        bookService.updateNameById(id, name);
    }

    @ShellMethod(value = "Find book by title", key = {"find by title", "find"})
    public void findBookByTitle() {
        ioService.output("Enter book title to search");
        String name = ioService.input();
        List<Book> allBooks = bookService.getByName(name);
        allBooks.forEach(ioService::output);
    }

    @ShellMethod(value = "Add comment to book by Id", key = {"add comment by book id", "addcom"})
    public void addCommentToBookById() {
        commentaryService.addNewCommentary();
    }

    @ShellMethod(value = "Show all comments to book by Id", key = {"comments to book by id", "bookcom"})
    public void showAllCommentsToBookById() {
        ioService.output("Enter book Id to show comments");
        long id = ioService.inputInt();
        List<Commentary> allComments = commentaryService.getAllCommentariesByBookId(id);
        ioService.output("Commentaries for book '" + bookService.getById(id).getTitle() + "':\r\n");
        allComments.forEach(ioService::output);
    }

    @ShellMethod(value = "Delete comment by Id", key = {"delete comment by id", "delcom"})
    public void deleteCommentById() {
        ioService.output("Enter comment Id to delete");
        long id = ioService.inputInt();
        commentaryService.deleteById(id);
    }

    @ShellMethod(value = "Edit comment text by id", key = {"edit comment by id", "editcom"})
    public void editCommentById() {
        ioService.output("Enter comment Id to change");
        long id = ioService.inputInt();
        ioService.output("Enter new comment");
        String text = ioService.input();
        commentaryService.updateTextById(id, text);
    }

    @ShellMethod(value = "Show all authors", key = {"author list", "allaut"})
    public void showAllAuthors() {
        List<Author> authors = authorService.getAll();
        authors.forEach(ioService::output);
    }

    @ShellMethod(value = "Show all books by author id", key = {"book list by author id", "allba"})
    public void showAllBooksByAuthorId() {
        ioService.output("Enter author id for showing his book list");
        long id = ioService.inputInt();
        List<Book> books = bookService.getAllBooksByAuthorId(id);
        ioService.output("Books by author: " + authorService.getById(id).getName());
        books.forEach(book -> ioService.output(book.getTitle()));
    }

    @ShellMethod(value = "Show all comments to all books by author id", key = {"comment list by author id", "allcomba"})
    public void showAllCommentsByAuthorId() {
        ioService.output("Enter author id for showing all comments list for his books");
        long id = ioService.inputInt();
        List<Commentary> comments = commentaryService.getAllCommentariesByAuthorId(id);
        ioService.output("Comments to books by author: " + authorService.getById(id).getName());
        comments.forEach(comment -> ioService.output(
                "Book: " + comment.getBook().getTitle() + ". Comment: " + comment.getText()
        ));
    }
}