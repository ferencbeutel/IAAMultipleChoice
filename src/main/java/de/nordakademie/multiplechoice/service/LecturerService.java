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
    public void saveLecturer(final Lecturer lecturer) {
        lecturerRepository.create(lecturer);
    }

    @Transactional
    public Lecturer updateLecturer(final Lecturer lecturer){
        return lecturerRepository.update(lecturer);
    }

    @Transactional(readOnly = true)
    public List<Lecturer> listAll() {
        return lecturerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lecturer byUserId (final long userId){
        return lecturerRepository.findByUserId(userId);
    }
}
