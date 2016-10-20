package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.LecturerRepository;
import de.nordakademie.multiplechoice.domain.TestRepository;
import de.nordakademie.multiplechoice.domain.UserRepository;
import de.nordakademie.multiplechoice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Melanie on 20.10.2016.
 */
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveStudent(final Lecturer lecturer, final String userNaturalId,
                            final String testNaturalId) {
        final User user = userRepository.find(userNaturalId);
        final Test test = testRepository.find(testNaturalId);

        lecturer.setUser(user);
        lecturer.setTest(test);

        lecturerRepository.createLecturer(lecturer);
    }

    @Transactional(readOnly = true)
    public List<Lecturer> listAll() {
        return lecturerRepository.findAll();
    }

    public Lecturer byNatID (final String natID){
        return lecturerRepository.find(natID);
    }

    public Lecturer changeLecturer(final Lecturer lecturer){
        return lecturerRepository.update(lecturer);
    }
}
