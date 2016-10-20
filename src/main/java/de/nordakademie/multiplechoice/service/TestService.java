package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.SeminarRepository;
import de.nordakademie.multiplechoice.domain.TestCompletionRepository;
import de.nordakademie.multiplechoice.domain.TestRepository;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Test;
import de.nordakademie.multiplechoice.model.TestCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SeminarRepository seminarRepository;
    @Autowired
    private TestCompletionRepository testCompletionRepository;


    @Transactional
    public void saveTest(final Test test, final String seminarNaturalId, final String testCompletionNaturalId) {
        final Seminar seminar = seminarRepository.find(seminarNaturalId);
        final TestCompletion testCompletion = testCompletionRepository.find(testCompletionNaturalId);
        test.setSeminar(seminar);
        test.setTestCompletion(testCompletion);
        testRepository.createTest(test);
    }

    @Transactional(readOnly = true)
    public List<Test> listAll() {
        return testRepository.findAll();
    }

    public Test byNatID (final String natID){
        return testRepository.find(natID);
    }

    public Test changeTest(final Test test){
        return testRepository.update(test);
    }


}
