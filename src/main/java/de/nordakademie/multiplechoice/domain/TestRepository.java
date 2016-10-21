package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Test;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class TestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Test test){
        entityManager.persist(test);
    }

    public List<Test> findAll(){
        return entityManager.createQuery("SELECT test FROM Test test", Test.class).getResultList();
    }

    public Test update(final Test updateTest){
        return entityManager.merge(updateTest);
    }
}
