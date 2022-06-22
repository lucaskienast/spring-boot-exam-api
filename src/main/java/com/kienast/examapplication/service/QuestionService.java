package com.kienast.examapplication.service;

import com.kienast.examapplication.model.PossibleAnswer;
import com.kienast.examapplication.model.Question;
import com.kienast.examapplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    // setup logging

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
    }


    public void saveQuestions(List<Question> questions) {
        for (Question question : questions) {
            this.questionRepository.save(question);
            Question savedQuestion = this.questionRepository.findQuestionByQuestionName(question.getQuestionName());

            List<PossibleAnswer> possibleAnswers = question.getPossibleAnswers();
            for (int i = 0; i < possibleAnswers.size(); i++) {
                possibleAnswers.get(i).setQuestion(savedQuestion);
            }

            this.answerService.savePossibleAnswers(possibleAnswers);
        }

    }


}
