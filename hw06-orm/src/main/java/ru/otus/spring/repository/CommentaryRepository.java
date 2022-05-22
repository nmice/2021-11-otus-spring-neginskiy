package ru.otus.spring.repository;

import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryRepository {

    Commentary save(Commentary commentary);

    List<Commentary> findByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    List<Commentary> findAllCommentariesByAuthorId(long id);

    void deleteByBookId(long id);
}
