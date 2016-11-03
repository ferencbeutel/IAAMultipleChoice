package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.TestResultRepository;
import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Transactional
    public TestResult createOrUpdate(final TestResult testResult) {
        return testResultRepository.createOrUpdate(testResult);
    }

    @Transactional(readOnly = true)
    public TestResult byId(long id) {
        return testResultRepository.byId(id);
    }
}
