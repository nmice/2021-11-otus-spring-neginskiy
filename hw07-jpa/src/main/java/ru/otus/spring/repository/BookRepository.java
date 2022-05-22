package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String name);

    @Query("update Book b set b.title=:name where b.id=:id")
    void updateNameById(long id, String name);

    List<Book> findByAuthorId(long id);
}
