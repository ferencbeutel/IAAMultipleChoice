package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Student student){
        entityManager.persist(student);
    }

    public List<Student> findAll(){
        return entityManager.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    public Student findByUserId(final long id) {
        try {
            return entityManager.createQuery(
                    "Select student FROM Student student WHERE user_id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Student update(final Student updateStudent){
        return entityManager.merge(updateStudent);
    }
}
