package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> findByBookId(long id);

    @Modifying
    @Query("update Commentary c set c.text=:text where c.id=:id")
    void updateTextById(@Param("id") long id, @Param("text") String text);

    List<Commentary> findByBookAuthorId(long id);

    void deleteByBookId(long id);
}
