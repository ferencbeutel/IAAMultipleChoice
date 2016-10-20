package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class AnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createAnswer(final Answer answer){
        entityManager.persist(answer);
    }

    public List<Answer> findAll(){
        return entityManager.createQuery("SELECT answer FROM Answer answer", Answer.class).getResultList();
    }

    public Answer find(final String answerNaturalId) {
        return entityManager.createQuery(
                "Select answer FROM Answer answer WHERE id = :answerNaturalId", Answer.class)
                .setParameter("answerNaturalId", answerNaturalId)
                .getSingleResult();
    }

    public Answer update(final Answer updateAnswer){
        return entityManager.merge(updateAnswer);
    }
}
