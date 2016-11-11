package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class is responsible for CRUD-Database operations for questions
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

@Repository
public class QuestionRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * This method persists a new question in the db
   *
   * @param question the question that should be persisted
   */
  public void create(final Question question) {
    entityManager.merge(question);
  }

  /**
   * This method persists the updates of question in the db
   *
   * @param updateQuestion the updated question that should be persisted
   *
   * @return the updated question
   */
  public Question update(final Question updateQuestion) {
    return entityManager.merge(updateQuestion);
  }

  /**
   * This method deletes a question with an forwarded id from the db
   *
   * @param questionId the id of the question that should be deleted
   */
  public void delete(final long questionId) {
    entityManager.remove(byId(questionId));
  }

  /**
   * This method finds all questions which are saved in the db
   *
   * @return a List of questions
   */
  public List<Question> findAll() {
    return entityManager.createQuery("SELECT question FROM Question question", Question.class).getResultList();
  }

  /**
   * This method finds a questions in the db by an specific id
   *
   * @return the question with the id = id
   */
  public Question byId(final long id) {
    try {
      return entityManager.createQuery("Select question FROM Question question WHERE questionId = :id", Question.class)
                          .setParameter("id", id).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
