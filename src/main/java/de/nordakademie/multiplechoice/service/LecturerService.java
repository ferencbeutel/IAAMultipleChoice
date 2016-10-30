package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.LecturerRepository;
import de.nordakademie.multiplechoice.model.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Transactional
    public void save(final Lecturer lecturer) {
        lecturerRepository.createOrUpdate(lecturer);
    }

    @Transactional(readOnly = true)
    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lecturer findById(final long id){
        return lecturerRepository.byId(id);
    }

    @Transactional(readOnly = true)
    public Lecturer findByMail(final String mail){
        return lecturerRepository.byMail(mail);
    }
}
