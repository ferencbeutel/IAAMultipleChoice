package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Repository
public class AnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Answer answer){
        entityManager.persist(answer);
    }

    public Answer update(final Answer updateAnswer){
        return entityManager.merge(updateAnswer);
    }
}
