package ru.otus.spring.service;


import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author getAuthor(String name) {
        if (!existAuthor(name)) authorRepository.insert(new Author(name));
        return authorRepository.getByName(name);
    }

    private boolean existAuthor(String name) {
        return authorRepository.checkByName(name);
    }
}
