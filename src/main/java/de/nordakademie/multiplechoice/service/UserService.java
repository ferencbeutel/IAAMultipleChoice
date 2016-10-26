package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.UserRepository;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Transactional
    public void saveUser(final User user) {
        userRepository.create(user);
    }

    @Transactional
    public User updateUser(final User user){
        return userRepository.update(user);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User byMail(final String natID){
        return userRepository.findByMail(natID);
    }

    @Transactional(readOnly = true)
    public User byRegToken(final String regToken) {
        return userRepository.findByRegToken(regToken);
    }

    @Transactional(readOnly = true)
    public boolean isUserStudent(User user) {
        if(studentService.byUserId(user.getId()) == null) {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public boolean isUserLecturer(User user) {
        if(lecturerService.byUserId(user.getId()) == null) {
            return false;
        }
        return true;
    }
}
