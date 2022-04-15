package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.domain.Genre;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre getById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    public Genre getGenre(String name) {
        if (!existGenre(name)) genreRepository.insert(new Genre(name));
        return genreRepository.getByName(name);
    }

    private boolean existGenre(String name) {
        return genreRepository.checkByName(name);
    }
}
