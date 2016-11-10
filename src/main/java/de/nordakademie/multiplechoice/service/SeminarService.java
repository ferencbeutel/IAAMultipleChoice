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
 * This class provides the functionality to interact with SeminarRepository
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class SeminarService {

    @Autowired
    private SeminarRepository seminarRepository;
    /**
     * This method calls the createOrUpdate method of SeminarRepository
     * @param seminar to be created or updated
     * @return persisted seminar
     */
    @Transactional
    public Seminar createOrUpdate(final Seminar seminar){
        return seminarRepository.createOrUpdate(seminar);
    }

    /**
     * This method calls the findAll method of SeminarRepository
     * @return list of seminars
     */
    @Transactional(readOnly = true)
    public List<Seminar> listAll() {
        return seminarRepository.findAll();
    }

    /**
     * This method calls the byId method of SeminarRepository
     * @param id to be searched
     * @return seminar that with the id
     */
    @Transactional(readOnly = true)
    public Seminar byId(final long id) {
        return seminarRepository.byId(id);
    }

    /**
     * This method calls the findAll method of SeminarRepository
     * The list of seminars will be checked for seminars that are starting on the current day
     * @return list of seminars starting on the day of execution
     */
    @Transactional(readOnly = true)
    public List<Seminar> allWithTestsStartingToday() {
        List<Seminar> allSeminars = seminarRepository.findAll();
        List<Seminar> seminarsWithTest = allSeminars.stream().filter(seminar -> seminar.getTest() != null).collect(Collectors.toList());
        return seminarsWithTest.stream().filter(seminar -> seminar.getTest().getBeginDate().isEqual(LocalDate.now())).collect(Collectors.toList());
    }
}
