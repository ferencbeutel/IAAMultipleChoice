package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


/**
 * This class is responsible for CRU(D)-Database operations for TestResults
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

@Repository
public class TestResultRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * This method persists a new testResult or persists the changes made to an existing testResult in db
   *
   * @param testResult new or updated instance of a testResult
   *
   * @return the saved testResult
   */
  public TestResult createOrUpdate(final TestResult testResult) {
    return entityManager.merge(testResult);
  }

  /**
   * This method finds a testResult from the database by the forwarded id
   *
   * @param id the id of the testResult
   *
   * @return the testResult with id = id
   */
  public TestResult byId(final long id) {
    try {
      return entityManager
          .createQuery("Select testResult FROM TestResult testResult WHERE testResultId = :id", TestResult.class)
          .setParameter("id", id).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
