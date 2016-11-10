package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.TestResultRepository;
import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class provides the functionality to interact with TestResultRepository
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    /**
     * This method calls the createOrUpdate method of StudentRepository
     * @param testResult to be created or updated
     * @return persisted TestResult
     */
    @Transactional
    public TestResult createOrUpdate(final TestResult testResult) {
        return testResultRepository.createOrUpdate(testResult);
    }

    /**
     * This method calls the byId method of StudentRepository
     * @param id of testresult that is looked for
     * @return TestResult with the id
     */
    @Transactional(readOnly = true)
    public TestResult byId(final long id) {
        return testResultRepository.byId(id);
    }
}
