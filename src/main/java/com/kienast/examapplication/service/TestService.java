package com.kienast.examapplication.service;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    public List<Test> getAllTests() {
        List<Test> testList = new ArrayList<>();
        return testList;
    }

    public Test getTestById(String testId) {
        Test test = new Test();
        return test;
    }

    public Test createNewTest(Test givenTest) {
        Test test = new Test();
        return test;
    }

    public String sendTestWithAnswers(List<GivenAnswer> givenAnswers) {
        return "success";
    }

}
