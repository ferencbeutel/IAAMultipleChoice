package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.AnswerRepository;
import de.nordakademie.multiplechoice.domain.QuestionRepository;
import de.nordakademie.multiplechoice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Melanie on 20.10.2016.
 */
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void saveAnswer(final Answer answer, final String questionNaturalId) {
        final Question question = questionRepository.find(questionNaturalId);
        answer.setQuestion(question);

        answerRepository.createAnswer(answer);

    }

    @Transactional(readOnly = true)
    public List<Answer> listAll() {
        return answerRepository.findAll();
    }

    public Answer byNatID (final String natID){
        return answerRepository.find(natID);
    }

    public Answer changeAnswer(final Answer answer){
        return answerRepository.update(answer);
    }

}
