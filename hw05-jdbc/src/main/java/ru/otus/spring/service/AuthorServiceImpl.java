package ru.otus.spring.service;


import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author getAuthor(String name) {
        if (!existAuthor(name)) authorDao.insert(new Author(name));
        return authorDao.getByName(name);
    }

    private boolean existAuthor(String name){
        return authorDao.checkByName(name);
    }
}
