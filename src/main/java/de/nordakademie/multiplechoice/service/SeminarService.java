package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.*;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class SeminarService {
    @Autowired
    private SeminarRepository seminarRepository;
    @Autowired
    private LecturerRepository lecturerRepository;


    @Transactional
    public void saveSeminar(final Seminar seminar, final String lecturerNaturalId) {
        final Lecturer lecturer = lecturerRepository.find(lecturerNaturalId);
        seminar.setLecturer(lecturer);
        seminarRepository.createSeminar(seminar);
    }

    @Transactional(readOnly = true)
    public List<Seminar> listAll() {
        return seminarRepository.findAll();
    }

    public Seminar byNatID (final String natID){
        return seminarRepository.find(natID);
    }

    public Seminar changeSeminar(final Seminar seminar){
        return seminarRepository.update(seminar);
    }


}
