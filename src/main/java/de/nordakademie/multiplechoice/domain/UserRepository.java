package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final User user){
        entityManager.persist(user);
    }

    public List<User> findAll(){
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User findByMail(final String mail) {
        try {
            return entityManager.createQuery(
                    "Select user FROM User user WHERE email = :id", User.class)
                    .setParameter("id", mail)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public User findByRegToken(final String regToken) {
        try {
            return entityManager.createQuery(
                    "Select user FROM User user WHERE regToken = :regToken", User.class)
                    .setParameter("regToken", regToken)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public User update(final User updateUser){
        return entityManager.merge(updateUser);
    }
}
