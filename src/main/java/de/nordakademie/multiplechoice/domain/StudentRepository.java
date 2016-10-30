package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Lecturer;
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

    public Student createOrUpdate(final Student student){
        return entityManager.merge(student);
    }

    public List<Student> findAll(){
        return entityManager.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    public Student byId(final long studentId) {
        try {
            return entityManager.createQuery(
                    "Select student FROM Student student WHERE userId = :id", Student.class)
                    .setParameter("id", studentId)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Student byMail(final String mail) {
        try {
            return entityManager.createQuery(
                    "SELECT student FROM Student student WHERE email = :mail", Student.class)
                    .setParameter("mail", mail)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Student byRegToken(final String regToken) {
        try {
            return entityManager.createQuery(
                    "SELECT student FROM Student student WHERE regToken = :regToken", Student.class)
                    .setParameter("regToken", regToken)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
