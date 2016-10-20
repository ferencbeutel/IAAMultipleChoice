package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.TestCompletion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class TestCompletionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createTestCompletion(final TestCompletion testCompletion){
        entityManager.persist(testCompletion);
    }

    public List<TestCompletion> findAll(){
        return entityManager.createQuery("SELECT testCompletion FROM TestCompletion testCompletion", TestCompletion.class).getResultList();
    }

    public TestCompletion find(final String testCompletionNaturalId) {
        return entityManager.createQuery(
                "Select testCompletion FROM TestCompletion testCompletion WHERE CONCAT(testCompletion.student, testCompletion.test) = :testCompletionNaturalId", TestCompletion.class)
                .setParameter("testCompletionNaturalId", testCompletionNaturalId)
                .getSingleResult();
    }

    public TestCompletion update(final TestCompletion updateTestCompletion){
        return entityManager.merge(updateTestCompletion);
    }
}
