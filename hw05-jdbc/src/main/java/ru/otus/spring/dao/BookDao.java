package ru.otus.spring.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public class BookDao {

    private NamedParameterJdbcOperations jdbcOperations;

    public void save(Book book) {
        jdbcOperations.update("insert into books values(name=:name,genre=:genre,author=:author)",
                book.getName(), book.getGenre(), book.getName());
    }
}
