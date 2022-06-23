package com.kienast.examapplication.service;

import com.kienast.examapplication.model.PossibleAnswer;
import com.kienast.examapplication.model.Question;
import com.kienast.examapplication.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerService answerService) {
        LOG.info("QuestionService: constructor called");
        this.questionRepository = questionRepository;
        this.answerService = answerService;
    }


    public void saveQuestions(List<Question> questions) {
        LOG.info("QuestionService: saveQuestions questions -> {}", questions);
        for (Question question : questions) {
            LOG.info("QuestionService: saveQuestions unsaved question -> {}", question);
            this.questionRepository.save(question);
            LOG.info("QuestionService: saveQuestions saved question -> {}", question);

            List<PossibleAnswer> possibleAnswers = question.getPossibleAnswers();
            for (int i = 0; i < possibleAnswers.size(); i++) {
                possibleAnswers.get(i).setQuestion(question);
            }

            this.answerService.savePossibleAnswers(possibleAnswers);
        }
    }


}
