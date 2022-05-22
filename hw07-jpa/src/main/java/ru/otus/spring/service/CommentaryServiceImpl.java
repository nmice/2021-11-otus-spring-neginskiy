package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.repository.CommentaryRepository;

import java.util.List;

@Service
public class CommentaryServiceImpl implements CommentaryService {
    private final CommentaryRepository commentaryRepository;
    private final InputOutputService ioService;
    private final BookService bookService;

    public CommentaryServiceImpl(CommentaryRepository commentaryRepository,
                                 InputOutputService ioService,
                                 BookService bookService) {
        this.commentaryRepository = commentaryRepository;
        this.ioService = ioService;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public Commentary save(Commentary commentary) {
        return commentaryRepository.save(commentary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Commentary> getAllCommentariesByBookId(long id) {
        Book book = bookService.getById(id);
        return commentaryRepository.findByBookId(id);
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        commentaryRepository.updateTextById(id, text);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public void addNewCommentary() {
        ioService.output("Enter book id to add commentary");
        int bookId = ioService.inputInt();
        Book book = bookService.getById(bookId);
        if (book != null) {
            ioService.output("Enter comment for book - " + book.getTitle());
            String commentText = ioService.input();
            Commentary commentary = new Commentary(commentText, book);
            save(commentary);
        } else {
            ioService.output("Book for id: " + bookId + " not found.");
        }
    }

    @Override
    public List<Commentary> getAllCommentariesByAuthorId(long id) {
        return commentaryRepository.findByBookAuthorId(id);
    }

    @Override
    @Transactional
    public void deleteByBookId(long id) {
        commentaryRepository.deleteByBookId(id);
    }
}
