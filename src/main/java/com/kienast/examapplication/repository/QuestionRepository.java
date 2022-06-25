package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.Question;
import com.kienast.examapplication.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findAllByTest(Test test);

}
