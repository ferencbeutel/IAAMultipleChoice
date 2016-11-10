package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class is responsible for administrative tasks and to prepare test data
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

@Repository
public class LecturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Lecturer createOrUpdate(final Lecturer lecturer) {
        return entityManager.merge(lecturer);
    }

    public List<Lecturer> findAll() {
        return entityManager.createQuery("SELECT lecturer FROM Lecturer lecturer", Lecturer.class).getResultList();
    }

    public Lecturer byId(final long lecturerId) {
        try {
            return entityManager.createQuery(
                    "Select lecturer FROM Lecturer lecturer WHERE userId = :id", Lecturer.class)
                    .setParameter("id", lecturerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Lecturer byMail(final String mail) {
        try {
            return entityManager.createQuery(
                    "SELECT lecturer FROM Lecturer lecturer WHERE email = :mail", Lecturer.class)
                    .setParameter("mail", mail)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
