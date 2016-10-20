package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.*;
import de.nordakademie.multiplechoice.domain.TestCompletionRepository;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.model.TestCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class TestCompletionService {
    @Autowired
    private TestCompletionRepository testCompletionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestRepository testRepository;

    @Transactional
    public void saveTestCompletion(final TestCompletion testCompletion, final String studentNaturalId,
                                   final String testNaturalId) {
        final Student student = studentRepository.find(studentNaturalId);
        final Test test = testRepository.find(testNaturalId);
        testCompletion.setStudent(student);
        testCompletion.setTest(test);
        testCompletionRepository.createTestCompletion(testCompletion);
    }

    @Transactional(readOnly = true)
    public List<TestCompletion> listAll() {
        return testCompletionRepository.findAll();
    }

    public TestCompletion byNatID (final String natID){
        return testCompletionRepository.find(natID);
    }

    public TestCompletion changeTestCompletion(final TestCompletion testCompletion){
        return testCompletionRepository.update(testCompletion);
    }


}
