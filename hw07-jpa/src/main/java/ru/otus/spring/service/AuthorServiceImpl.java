package ru.otus.spring.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Author with id %d not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }
}
