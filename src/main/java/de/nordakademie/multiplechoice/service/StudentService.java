package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.SeminarRepository;
import de.nordakademie.multiplechoice.domain.StudentRepository;
import de.nordakademie.multiplechoice.domain.TestCompletionRepository;
import de.nordakademie.multiplechoice.domain.UserRepository;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.TestCompletion;
import de.nordakademie.multiplechoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeminarRepository seminarRepository;
    @Autowired
    private TestCompletionRepository testCompletionRepository;

    @Transactional
    public void saveStudent(final Student student, final String userNaturalId,
                            final String seminarNaturalId, final String testCompletionNaturalId) {
        final User user = userRepository.find(userNaturalId);
        final Seminar seminar = seminarRepository.find(seminarNaturalId);
        final TestCompletion testCompletion = testCompletionRepository.find(testCompletionNaturalId);
        student.setUser(user);
        student.setSeminar(seminar);
        student.setTestCompletion(testCompletion);
        studentRepository.createStudent(student);
    }

    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public Student byNatID (final String natID){
        return studentRepository.find(natID);
    }

    public Student changeStudent(final Student student){
        return studentRepository.update(student);
    }


}
