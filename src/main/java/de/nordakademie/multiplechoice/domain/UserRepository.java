package de.nordakademie.multiplechoice.domain;

import de.nordakademie.multiplechoice.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(final User user){
        entityManager.persist(user);
    }

    public List<User> findAll(){
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User find(final String userNaturalId) {
        return entityManager.createQuery(
                "Select user FROM User user WHERE email = :userNaturalId", User.class)
                .setParameter("userNaturalId", userNaturalId)
                .getSingleResult();
    }

    public User update(final User updateUser){
        return entityManager.merge(updateUser);
    }
}
