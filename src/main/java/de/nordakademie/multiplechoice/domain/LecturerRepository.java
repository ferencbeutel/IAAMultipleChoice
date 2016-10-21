package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class LecturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Lecturer lecturer){
        entityManager.persist(lecturer);
    }

    public List<Lecturer> findAll(){
        return entityManager.createQuery("SELECT lecturer FROM Lecturer lecturer", Lecturer.class).getResultList();
    }

    public Lecturer findByUserId(final long id) {
        try {
            return entityManager.createQuery(
                    "Select lecturer FROM Lecturer lecturer WHERE user_id = :id", Lecturer.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Lecturer update(final Lecturer updateLecturer){
        return entityManager.merge(updateLecturer);
    }
}
