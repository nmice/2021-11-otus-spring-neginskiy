package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
}
