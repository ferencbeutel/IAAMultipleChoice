package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.TestRepository;
import de.nordakademie.multiplechoice.model.Test;
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

    @Transactional
    public void saveTest(final Test test) {
        testRepository.create(test);
    }

    @Transactional
    public Test updateTest(final Test test){
        return testRepository.update(test);
    }

    @Transactional(readOnly = true)
    public List<Test> listAll() {
        return testRepository.findAll();
    }
}
