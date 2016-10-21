package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.AnswerRepository;
import de.nordakademie.multiplechoice.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public void saveAnswer(final Answer answer) {
        answerRepository.create(answer);
    }

    @Transactional
    public Answer updateAnswer(final Answer answer){
        return answerRepository.update(answer);
    }
}
