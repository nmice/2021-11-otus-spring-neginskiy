package ru.otus.spring.repository;

import ru.otus.spring.domain.Commentary;

public interface CommentaryRepository {

    Commentary insert(Commentary genre);

    Commentary getById(long id);

    boolean checkByName(String description);

    Commentary getByName(String description);
}
