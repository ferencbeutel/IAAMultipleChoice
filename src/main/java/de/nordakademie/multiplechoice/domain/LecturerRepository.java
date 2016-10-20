package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class LecturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createLecturer(final Lecturer lecturer){
        entityManager.persist(lecturer);
    }

    public List<Lecturer> findAll(){
        return entityManager.createQuery("SELECT lecturer FROM Lecturer lecturer", Lecturer.class).getResultList();
    }

    public Lecturer find(final String lecturerNaturalId) {
        return entityManager.createQuery(
                "Select lecturer FROM Lecturer lecturer WHERE userMail = :lecturerNaturalId", Lecturer.class)
                .setParameter("lecturerNaturalId", lecturerNaturalId)
                .getSingleResult();
    }

    public Lecturer update(final Lecturer updateLecturer){
        return entityManager.merge(updateLecturer);
    }
}
