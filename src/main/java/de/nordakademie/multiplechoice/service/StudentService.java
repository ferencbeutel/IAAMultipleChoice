package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.StudentRepository;
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
    public Student save(final Student student){
        return studentRepository.createOrUpdate(student);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(final long id){
        return studentRepository.byId(id);
    }

    @Transactional(readOnly = true)
    public Student findByMail(final String mail){
        return studentRepository.byMail(mail);
    }

    @Transactional(readOnly = true)
    public Student findByRegToken(final String regToken) {
        return studentRepository.byRegToken(regToken);
    }

}
