package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryRepository extends CrudRepository<Commentary, Long> {

    List<Commentary> findByBookId(long id);

    @Query("update Commentary c set c.text=:text where c.id=:id")
    void updateTextById(long id, String text);

    List<Commentary> findByBookAuthorId(long id);

    void deleteByBookId(long id);
}
