package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GivenAnswerRepository extends JpaRepository<GivenAnswer, Long> {

    public List<GivenAnswer> findGivenAnswerByTestResult(TestResult testResult);

}
