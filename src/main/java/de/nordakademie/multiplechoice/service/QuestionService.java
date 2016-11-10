package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.domain.QuestionRepository;
import de.nordakademie.multiplechoice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class provides the functionality to interact with QuestionRepository
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    /**
     * This method calls the create method of QuestionRepository
     * @param question to be created
     * @return persisted question
     */
    @Transactional
    public void saveQuestion(final Question question) {
        questionRepository.create(question);
    }

    /**
     * This method calls the delete method of QuestionRepository
     * @param questionId of question to delete
     */
    @Transactional
    public void deleteQuestion(final long questionId) {
        questionRepository.delete(questionId);
    }
    @Transactional(readOnly = true)
    /**
     * This method calls the findAll method of QuestionRepository
     * @return list of questions
     */
    public List<Question> listAll() {
        return questionRepository.findAll();
    }
    @Transactional
    /**
     * This method calls the update method of QuestionRepository
     * @param question to be  updated
     * @return persisted question
     */
    public Question updateQuestion(final Question question){
        return questionRepository.update(question);
    }

    /**
     * This method calls the byId method of QuestionRepository
     * @param id to be searched
     * @return question with the id
     */
    @Transactional(readOnly = true)
    public Question byId (final long id) {
        return questionRepository.byId(id);
    }
}
