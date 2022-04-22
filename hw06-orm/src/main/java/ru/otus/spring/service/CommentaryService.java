package ru.otus.spring.service;

import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryService {

    Commentary save(Commentary commentary);

    List<Commentary> findByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    void addNewCommentary();

    List<Commentary> findAllCommentariesByAuthorId(long id);

    void deleteByBookId(long id);
}
