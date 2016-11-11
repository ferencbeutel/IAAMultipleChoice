package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This class is responsible for CRU(D)-Database operations for seminars
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

@Repository
public class SeminarRepository {
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * This method persists a new seminar or persists the changes made to an existing seminar in db
   *
   * @param updateSeminar new or updated instance of a seminar
   *
   * @return the saved seminar
   */
  public Seminar createOrUpdate(final Seminar updateSeminar) {
    return entityManager.merge(updateSeminar);
  }

  /**
   * This finds all seminars which are saved in the database
   *
   * @return List of seminars
   */
  public List<Seminar> findAll() {
    return entityManager.createQuery("SELECT seminar FROM Seminar seminar", Seminar.class).getResultList();
  }

  /**
   * This method finds a seminar from the database by the forwarded id
   *
   * @param id the id of the seminar
   *
   * @return the seminar with id = id
   */
  public Seminar byId(final long id) {
    try {
      return entityManager.createQuery("Select seminar FROM Seminar seminar WHERE seminarId = :id", Seminar.class)
                          .setParameter("id", id).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
