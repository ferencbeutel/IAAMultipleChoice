package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.StudentRepository;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
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

    @Transactional
    public Student createOrUpdate(final Student student){
        return studentRepository.createOrUpdate(student);
    }

    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student byUserId (final long userId){
        return studentRepository.findByUserId(userId);
    }
}
