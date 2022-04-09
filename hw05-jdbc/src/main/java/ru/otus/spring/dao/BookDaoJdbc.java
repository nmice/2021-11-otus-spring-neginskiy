package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public long getNextId() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select next value for BOOK_SEQUENCE", Long.class);
    }

    @Override
    public int count() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from BOOKS", Integer.class);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> params = Map.of(
                "id", book.getId(),
                "name", book.getTitle(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "insert into BOOKS (ID, BOOK_NAME, AUTHOR_ID, GENRE_ID) values (:id, :name, :author_id, :genre_id)", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.ID, b.BOOK_NAME, " +
                        "a.ID as AUTHOR_ID, a.AUTHOR_NAME, " +
                        "g.ID as GENRE_ID, g.GENRE_NAME " +
                        "from BOOKS b " +
                        "inner join AUTHORS a " +
                        "on a.ID = b.AUTHOR_ID " +
                        "inner join GENRES g " +
                        "on g.ID = b.GENRE_ID " +
                        "where b.ID = :id", params,
                new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.ID, b.BOOK_NAME, " +
                        "b.AUTHOR_ID, a.AUTHOR_NAME, " +
                        "b.GENRE_ID, g.GENRE_NAME " +
                        "from BOOKS b " +
                        "inner join AUTHORS a " +
                        "on a.ID = b.AUTHOR_ID " +
                        "inner join GENRES g " +
                        "on g.ID = b.GENRE_ID",
                new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from BOOKS where ID = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(
                    resultSet.getLong("ID"),
                    resultSet.getString("BOOK_NAME"),
                    new Author(resultSet.getLong("AUTHOR_ID"),
                            resultSet.getString("AUTHOR_NAME")),
                    new Genre(resultSet.getLong("GENRE_ID"),
                            resultSet.getString("GENRE_NAME")));
        }
    }
}
