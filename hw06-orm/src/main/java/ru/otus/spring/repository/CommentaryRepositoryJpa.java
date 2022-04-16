package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentaryRepositoryJpa implements CommentaryRepository {

    @PersistenceContext
    private final EntityManager em;

    public CommentaryRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Commentary insert(Commentary genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Commentary getById(long id) {
        return em.find(Commentary.class, id);
    }

    @Override
    public boolean checkByName(String name) {
        var query = em.createQuery("select count(g) from Commentary g where g.content = :name", Long.class);
        query.setParameter("name", name);
        var count = query.getSingleResult();
        return count != null && count > 0;
    }

    @Override
    public Commentary getByName(String name) {
        var query = em.createQuery("select g from Commentary g where g.content = :name", Commentary.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
