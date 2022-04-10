package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getGenre(String name) {
        if (!existGenre(name)) genreDao.insert(new Genre(name));
        return genreDao.getByName(name);
    }

    private boolean existGenre(String name) {
        return genreDao.checkByName(name);
    }
}
