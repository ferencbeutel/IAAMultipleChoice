package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Question;
import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Question question){
        entityManager.merge(question);
    }

    public Question update(final Question updateQuestion){
        return entityManager.merge(updateQuestion);
    }

    public List<Question> findAll(){
        return entityManager.createQuery("SELECT question FROM Question question", Question .class).getResultList();
    }
    public Question byId(final long id) {
        try {
            return entityManager.createQuery(
                    "Select question FROM Question question WHERE questionId = :id", Question.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

}
