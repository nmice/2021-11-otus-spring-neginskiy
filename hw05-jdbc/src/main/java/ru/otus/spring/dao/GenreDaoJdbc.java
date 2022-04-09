package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public long getNextId() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select next value for GENRE_SEQUENCE", Long.class);
    }

    @Override
    public int count() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from GENRES", Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        Map<String, Object> params = Map.of("id", genre.getId(), "name", genre.getName());
        namedParameterJdbcOperations.update("insert into GENRES (ID, GENRE_NAME) values (:id, :name)", params);
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = Map.of("id", genre.getId(), "name", genre.getName());
        namedParameterJdbcOperations.update("update GENRES set ID = :id, GENRE_NAME = :name where ID = :id", params);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, GENRE_NAME from GENRES where ID = :id", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select ID, GENRE_NAME from GENRES", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from GENRES where ID = :id", params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("GENRE_NAME");
            return new Genre(id, name);
        }
    }
}
