package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class SeminarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createSeminar(final Seminar seminar){
        entityManager.persist(seminar);
    }

    public List<Seminar> findAll(){
        return entityManager.createQuery("SELECT seminar FROM Seminar seminar", Seminar.class).getResultList();
    }

    public Seminar find(final String seminarNaturalId) {
        return entityManager.createQuery(
                "Select seminar FROM Seminar seminar WHERE CONCAT(seminar.name, seminar.startDate, seminar.endDate) = :seminarNaturalId", Seminar.class)
                .setParameter("seminarNaturalId", seminarNaturalId)
                .getSingleResult();
    }

    public Seminar update(final Seminar updateSeminar){
        return entityManager.merge(updateSeminar);
    }
}
