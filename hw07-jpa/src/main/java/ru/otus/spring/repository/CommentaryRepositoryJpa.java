package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentaryRepositoryJpa implements CommentaryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Commentary save(Commentary commentary) {
        if (commentary.getId() == null) {
            em.persist(commentary);
            return commentary;
        } else {
            return em.merge(commentary);
        }
    }

    @Override
    public List<Commentary> findByBookId(long id) {
        TypedQuery<Commentary> query = em.createQuery(
                "select c from Commentary c where c.book.id=:id",
                Commentary.class
        );
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void updateTextById(long id, String text) {
        Query query = em.createQuery("update Commentary c set c.text=:text where c.id=:id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Commentary c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Commentary> findAllCommentariesByAuthorId(long id) {
        TypedQuery<Commentary> query = em.createQuery(
                "select c from Commentary c left join c.book b where b.author.id=:id",
                Commentary.class
        );
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void deleteByBookId(long id) {
        Query query = em.createQuery(
                "delete from Commentary c where c.book.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
