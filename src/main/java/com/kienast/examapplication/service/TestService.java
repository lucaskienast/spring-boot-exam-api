package com.kienast.examapplication.service;

import com.kienast.examapplication.dto.CreateTestDto;
import com.kienast.examapplication.dto.SendTestDto;
import com.kienast.examapplication.model.*;
import com.kienast.examapplication.repository.TestRepository;
import com.kienast.examapplication.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    // setup logging

    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final AuthService authService;
    private final QuestionService questionService;
    private final AnswerService answerService;


    @Autowired
    public TestService(TestRepository testRepository, TestResultRepository testResultRepository, AuthService authService, QuestionService questionService, AnswerService answerService) {
        this.testRepository = testRepository;
        this.testResultRepository = testResultRepository;
        this.authService = authService;
        this.questionService = questionService;
        this.answerService = answerService;
    }


    public List<Test> getAllTests() {
        List<Test> testList = this.testRepository.findAll();
        return testList;
    }


    public Test getTestById(Long testId) {
        Optional<Test> optionalTest = this.testRepository.findById(testId);

        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            return test;
        }

        return null;
    }


    public String createNewTest(CreateTestDto createTestDto) {
        User givenUser = new User();
        givenUser.setUsername(createTestDto.getUser().getUsername());
        givenUser.setPassword(createTestDto.getUser().getPassword());

        User user = this.authService.login(givenUser);

        if (user == null) {
            return "Test creation failed, because user credentials not valid.";
        }

        Test test = new Test();
        test.setTestName(createTestDto.getTest().getTestName());
        test.setCreatedBy(user);
        this.testRepository.save(test);
        Test savedTest = this.testRepository.findTestByTestName(test.getTestName());

        if (savedTest == null) {
            return "Test creation failed, because test not saved.";
        }

        List<Question> questions = createTestDto.getTest().getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setTest(savedTest);
        }

        this.questionService.saveQuestions(questions);

        return "Test creation successful!";
    }


    public String sendTestWithAnswers(SendTestDto sendTestDto) {
        User givenUser = new User();
        givenUser.setUsername(sendTestDto.getUser().getUsername());
        givenUser.setPassword(sendTestDto.getUser().getPassword());

        User user = this.authService.login(givenUser);

        if (user == null) {
            return "Test sending failed, because user not found.";
        }

        Test test = getTestById(sendTestDto.getTestResult().getTest().getTestId());

        TestResult testResult = new TestResult();
        testResult.setResult(sendTestDto.getTestResult().getResult());
        testResult.setTest(test);
        testResult.setCreatedBy(user);
        this.testResultRepository.save(testResult);

        List<GivenAnswer> givenAnswers = sendTestDto.getGivenAnswerList();
        for (int i = 0; i < givenAnswers.size(); i++) {
            givenAnswers.get(i).setTestResult(testResult);
        }

        this.answerService.saveGivenAnswers(givenAnswers);

        return "Test sending successful!";
    }


}
