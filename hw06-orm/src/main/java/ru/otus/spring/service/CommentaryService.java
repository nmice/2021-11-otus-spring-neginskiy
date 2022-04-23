package ru.otus.spring.service;

import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryService {

    Commentary insert(Commentary commentary);

    List<Commentary> getByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    void addNewCommentary();

    List<Commentary> getAllCommentariesByAuthorId(long id);

    void deleteByBookId(long id);
}
