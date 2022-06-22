package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public Question findQuestionByQuestionName(String questionName);

}
