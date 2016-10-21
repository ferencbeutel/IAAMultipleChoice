package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.QuestionRepository;
import de.nordakademie.multiplechoice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void saveQuestion(final Question question) {
        questionRepository.create(question);
    }

    @Transactional
    public Question updateQuestion(final Question question){
        return questionRepository.update(question);
    }
}
