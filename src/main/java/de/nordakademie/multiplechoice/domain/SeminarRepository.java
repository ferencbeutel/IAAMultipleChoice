package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Repository
public class SeminarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Seminar seminar) {
        entityManager.persist(seminar);
    }

    public List<Seminar> findAll() {
        return entityManager.createQuery("SELECT seminar FROM Seminar seminar", Seminar.class).getResultList();
    }

    public Seminar update(final Seminar updateSeminar) {
        return entityManager.merge(updateSeminar);
    }

    public Seminar byId(final long id) {
        try {
            return entityManager.createQuery(
                    "Select seminar FROM Seminar seminar WHERE seminarId = :id", Seminar.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
