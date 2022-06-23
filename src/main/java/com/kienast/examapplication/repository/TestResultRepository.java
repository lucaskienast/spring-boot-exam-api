package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.Test;
import com.kienast.examapplication.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    public List<TestResult> findTestResultsByTest(Test test);

}
