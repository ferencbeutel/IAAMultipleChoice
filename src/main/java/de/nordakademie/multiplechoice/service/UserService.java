package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.UserRepository;
import de.nordakademie.multiplechoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(final User user) {
        userRepository.createUser(user);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User byNatID (final String natID){
        return userRepository.find(natID);
    }

    public User changeUser(final User user){
        return userRepository.update(user);
    }


}
