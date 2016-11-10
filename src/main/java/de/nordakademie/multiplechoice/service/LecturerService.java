package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.LecturerRepository;
import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class provides the functionality to interact with LecturerRepository
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    /**
     * This method calls the createOrUpdate method of LecturerRepository
     * @param lecturer to be created or updated
     * @return persisted lecturer
     */
    @Transactional
    public Lecturer createOrUpdate(final Lecturer lecturer) {
        return lecturerRepository.createOrUpdate(lecturer);
    }

    /**
     * This method calls the findAll method of LecturerRepository
     * @return persisted lecturer
     */
    @Transactional(readOnly = true)
    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    /**
     * This method calls the findById method of LecturerRepository
     * @param id of lecturer
     * @return persisted lecturer
     */
    @Transactional(readOnly = true)
    public Lecturer findById(final long id){
        return lecturerRepository.byId(id);
    }

    /**
     * This method calls the findByMail method of LecturerRepository
     * @param mail of lecturer
     * @return persisted lecturer
     */
    @Transactional(readOnly = true)
    public Lecturer findByMail(final String mail){
        return lecturerRepository.byMail(mail);
    }
}
