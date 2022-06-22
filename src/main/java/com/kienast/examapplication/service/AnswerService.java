package com.kienast.examapplication.service;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.PossibleAnswer;
import com.kienast.examapplication.repository.GivenAnswerRepository;
import com.kienast.examapplication.repository.PossibleAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnswerService {

    // setup logging

    private final PossibleAnswerRepository possibleAnswerRepository;
    private final GivenAnswerRepository givenAnswerRepository;


    @Autowired
    public AnswerService(PossibleAnswerRepository possibleAnswerRepository, GivenAnswerRepository givenAnswerRepository) {
        this.possibleAnswerRepository = possibleAnswerRepository;
        this.givenAnswerRepository = givenAnswerRepository;
    }


    public void savePossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        for (PossibleAnswer possibleAnswer : possibleAnswers) {
            this.possibleAnswerRepository.save(possibleAnswer);
        }
    }


    public void saveGivenAnswers(List<GivenAnswer> givenAnswers) {
        for (GivenAnswer givenAnswer : givenAnswers) {
            PossibleAnswer possibleAnswer = getPossibleAnswerById(givenAnswer.getAnswer().getPossibleAnswerId());
            givenAnswer.setAnswer(possibleAnswer);
            this.givenAnswerRepository.save(givenAnswer);
        }
    }


    private PossibleAnswer getPossibleAnswerById(Long possibleAnswerId) {
        Optional<PossibleAnswer> optionalPossibleAnswer = this.possibleAnswerRepository.findById(possibleAnswerId);

        if (optionalPossibleAnswer.isPresent()) {
            PossibleAnswer possibleAnswer = optionalPossibleAnswer.get();
            return possibleAnswer;
        }

        return null;
    }

}
