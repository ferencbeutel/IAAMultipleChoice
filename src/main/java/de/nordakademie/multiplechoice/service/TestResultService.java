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
    public void saveTestResult(final TestResult testResult) {
        testResultRepository.create(testResult);
    }

    @Transactional
    public TestResult updateTestResult(final TestResult testResult){
        return testResultRepository.update(testResult);
    }

    @Transactional(readOnly = true)
    public List<TestResult> listAll() {
        return testResultRepository.findAll();
    }
}
