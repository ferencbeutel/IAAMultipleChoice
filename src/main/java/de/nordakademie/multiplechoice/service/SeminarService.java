package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.SeminarRepository;
import de.nordakademie.multiplechoice.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class SeminarService {

    @Autowired
    private SeminarRepository seminarRepository;

    @Transactional
    public Seminar createOrUpdate(final Seminar seminar){
        return seminarRepository.createOrUpdate(seminar);
    }

    @Transactional(readOnly = true)
    public List<Seminar> listAll() {
        return seminarRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Seminar byId(final long id) {
        return seminarRepository.byId(id);
    }

    @Transactional(readOnly = true)
    public List<Seminar> allWithTestsStartingToday() {
        List<Seminar> allSeminars = seminarRepository.findAll();
        List<Seminar> seminarsWithTest = allSeminars.stream().filter(seminar -> seminar.getTest() != null).collect(Collectors.toList());
        return seminarsWithTest.stream().filter(seminar -> seminar.getTest().getBeginDate().isEqual(LocalDate.now())).collect(Collectors.toList());
    }
}
