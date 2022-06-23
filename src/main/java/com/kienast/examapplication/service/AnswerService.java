package com.kienast.examapplication.service;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.PossibleAnswer;
import com.kienast.examapplication.repository.GivenAnswerRepository;
import com.kienast.examapplication.repository.PossibleAnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AnswerService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionService.class);

    private final PossibleAnswerRepository possibleAnswerRepository;
    private final GivenAnswerRepository givenAnswerRepository;


    @Autowired
    public AnswerService(PossibleAnswerRepository possibleAnswerRepository, GivenAnswerRepository givenAnswerRepository) {
        LOG.info("AnswerService: constructor called");
        this.possibleAnswerRepository = possibleAnswerRepository;
        this.givenAnswerRepository = givenAnswerRepository;
    }


    public void savePossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        LOG.info("AnswerService: savePossibleAnswers possibleAnswers -> {}", possibleAnswers);
        for (PossibleAnswer possibleAnswer : possibleAnswers) {
            LOG.info("AnswerService: savePossibleAnswers unsaved possibleAnswer -> {}", possibleAnswer);
            this.possibleAnswerRepository.save(possibleAnswer);
            LOG.info("AnswerService: savePossibleAnswers saved possibleAnswer -> {}", possibleAnswer);
        }
    }


    public void saveGivenAnswers(List<GivenAnswer> givenAnswers) {
        LOG.info("AnswerService: saveGivenAnswers givenAnswers -> {}", givenAnswers);
        for (GivenAnswer givenAnswer : givenAnswers) {
            PossibleAnswer possibleAnswer = getPossibleAnswerById(givenAnswer.getAnswer().getPossibleAnswerId());
            givenAnswer.setAnswer(possibleAnswer);
            givenAnswer.setCreatedAt(new Date());
            LOG.info("AnswerService: saveGivenAnswers unsaved givenAnswer -> {}", givenAnswer);
            this.givenAnswerRepository.save(givenAnswer);
            LOG.info("AnswerService: saveGivenAnswers saved givenAnswer -> {}", givenAnswer);
        }
    }


    public PossibleAnswer getPossibleAnswerById(Long possibleAnswerId) {
        LOG.info("AnswerService: getPossibleAnswerById possibleAnswerId -> {}", possibleAnswerId);
        Optional<PossibleAnswer> optionalPossibleAnswer = this.possibleAnswerRepository.findById(possibleAnswerId);

        if (optionalPossibleAnswer.isPresent()) {
            PossibleAnswer possibleAnswer = optionalPossibleAnswer.get();
            LOG.info("AnswerService: getPossibleAnswerById found possibleAnswer -> {}", possibleAnswer);
            return possibleAnswer;
        }

        LOG.info("AnswerService: getPossibleAnswerById did not find possibleAnswer with id -> {}", possibleAnswerId);
        return null;
    }

}
