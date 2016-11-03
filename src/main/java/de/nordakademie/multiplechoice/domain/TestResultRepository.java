package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class TestResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TestResult createOrUpdate(final TestResult testResult) {
        return entityManager.merge(testResult);
    }

    public TestResult byId(final long id) {
        try {
            return entityManager.createQuery(
                    "Select testResult FROM TestResult testResult WHERE testResultId = :id", TestResult.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
