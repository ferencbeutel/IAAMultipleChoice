package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.StudentRepository;
import de.nordakademie.multiplechoice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class provides the functionality to interact with StudentRepository
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * This method calls the createOrUpdate method of StudentRepository
     * @param student to be created or updated
     * @return persisted student
     */
    @Transactional
    public Student createOrUpdate(final Student student){
        return studentRepository.createOrUpdate(student);
    }

    /**
     * This method calls the findAll method of StudentRepository
     * @return list of students
     */
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * This method calls the byId method of StudentRepository
     * @param id to be serached
     * @return student with the id
     */
    @Transactional(readOnly = true)
    public Student findById(final long id){
        return studentRepository.byId(id);
    }

    /**
     * This method calls the byMail method of StudentRepository
     * @param mail to be searched
     * @return student with the mail
     */
    @Transactional(readOnly = true)
    public Student findByMail(final String mail){
        return studentRepository.byMail(mail);
    }
    /**
     * This method calls the byRegToken method of StudentRepository
     * @param regToken to be searched
     * @return student with the regtoken
     */
    @Transactional(readOnly = true)
    public Student findByRegToken(final String regToken) {
        return studentRepository.byRegToken(regToken);
    }

}
