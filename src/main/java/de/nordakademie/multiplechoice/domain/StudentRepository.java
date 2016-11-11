package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class is responsible for CRU(D)-Database operations for students
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Repository
public class StudentRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * This method persists a new student or persists the changes made to an existing student in db
   *
   * @param student new or updated instance of a student
   *
   * @return the saved student
   */
  public Student createOrUpdate(final Student student) {
    return entityManager.merge(student);
  }

  /**
   * This finds all students which are saved in the database
   *
   * @return List of students
   */
  public List<Student> findAll() {
    return entityManager.createQuery("SELECT student FROM Student student", Student.class).getResultList();
  }

  /**
   * This method finds a student from the database by the forwarded id
   *
   * @param studentId the id of the student (user)
   *
   * @return the lecturer with id = studentId
   */
  public Student byId(final long studentId) {
    try {
      return entityManager.createQuery("Select student FROM Student student WHERE userId = :id", Student.class)
                          .setParameter("id", studentId).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  /**
   * This method finds a student from the database by his mail
   *
   * @param mail the mail of the student (user)
   *
   * @return the student with mail = mail
   */
  public Student byMail(final String mail) {
    try {
      return entityManager.createQuery("SELECT student FROM Student student WHERE email = :mail", Student.class)
                          .setParameter("mail", mail).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  /**
   * This method finds a student by the regtoken which is saved in db
   *
   * @param regToken the token of a user
   *
   * @return the student where token = regToken
   */
  public Student byRegToken(final String regToken) {
    try {
      return entityManager.createQuery("SELECT student FROM Student student WHERE regToken = :regToken", Student.class)
                          .setParameter("regToken", regToken).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
