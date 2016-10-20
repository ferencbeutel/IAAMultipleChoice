package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createStudent(final Student student){
        entityManager.persist(student);
    }

    public List<Student> findAll(){
        return entityManager.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    public Student find(final String studentNaturalId) {
        return entityManager.createQuery(
                "Select student FROM Student student WHERE userMail = :studentNaturalId", Student.class)
                .setParameter("studentNaturalId", studentNaturalId)
                .getSingleResult();
    }

    public Student update(final Student updateStudent){
        return entityManager.merge(updateStudent);
    }
}
