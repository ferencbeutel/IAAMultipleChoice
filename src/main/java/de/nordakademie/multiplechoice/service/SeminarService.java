package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.SeminarRepository;
import de.nordakademie.multiplechoice.model.Lecturer;
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

    @Transactional
    public void saveSeminar(final Seminar seminar) {
        seminarRepository.create(seminar);
    }

    @Transactional
    public Seminar updateSeminar(final Seminar seminar){
        return seminarRepository.update(seminar);
    }

    @Transactional(readOnly = true)
    public List<Seminar> listAll() {
        return seminarRepository.findAll();
    }
}
