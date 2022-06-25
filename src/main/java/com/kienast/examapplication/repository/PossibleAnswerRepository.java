package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.PossibleAnswer;
import com.kienast.examapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

    public List<PossibleAnswer> findAllByQuestion(Question question);

}
