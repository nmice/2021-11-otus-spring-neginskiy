package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int getCount() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from book", Integer.class);
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("authorId", book.getAuthor().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book (title, genreId, authorId) values (:title, :genre_id, :author_id)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.name genreName " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id " +
                        "where b.id = :id",
                params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.name genreName " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id",
                new BookMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    new Author(resultSet.getLong("authorId"),
                            resultSet.getString("authorName")),
                    new Genre(resultSet.getLong("genreId"),
                            resultSet.getString("genreName")));
        }
    }
}
