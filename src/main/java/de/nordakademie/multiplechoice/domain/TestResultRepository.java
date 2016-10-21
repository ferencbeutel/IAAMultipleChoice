package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class TestResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final TestResult testResult){
        entityManager.persist(testResult);
    }

    public List<TestResult> findAll(){
        return entityManager.createQuery("SELECT testResult FROM TestResult testResult", TestResult.class).getResultList();
    }

    public TestResult update(final TestResult updateTestResult){
        return entityManager.merge(updateTestResult);
    }
}
