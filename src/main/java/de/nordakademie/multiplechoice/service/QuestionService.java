package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.LecturerRepository;
import de.nordakademie.multiplechoice.domain.QuestionRepository;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHORT on 20.10.2016.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    

    @Transactional
    public void saveQuestion(final Question question) {
        questionRepository.createQuestion(question);
    }

    @Transactional(readOnly = true)
    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    public Question byNatID (final String natID){
        return questionRepository.find(natID);
    }

    public Question changeQuestion(final Question question){
        return questionRepository.update(question);
    }


}
