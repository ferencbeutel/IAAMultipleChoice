package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class is responsible for CRU(D)-Database operations for lecturers
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

@Repository
public class LecturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method persists a new lecturer or persists the changes made to an existing lecturer in db
     * @param lecturer new or updated instance of a lecturer
     * @return the saved lecturer
     */
    public Lecturer createOrUpdate(final Lecturer lecturer) {
        return entityManager.merge(lecturer);
    }

    /**
     * This finds all lecturer which are saved in the database
     * @return List of lecturers
     */
    public List<Lecturer> findAll() {
        return entityManager.createQuery("SELECT lecturer FROM Lecturer lecturer", Lecturer.class).getResultList();
    }

    /**
     * This method finds a lecturer from the database by the forwarded id
     * @param lecturerId the id of the lecturer (user)
     * @return the lecturer with id = lecturerId
     */
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

    /**
     * This method finds a lecturer from the database by his mail
     * @param mail the mail of the lecturer(user)
     * @return the lecturer with mail = mail
     */
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
