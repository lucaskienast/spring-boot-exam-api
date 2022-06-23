package com.kienast.examapplication.service;

import com.kienast.examapplication.dto.*;
import com.kienast.examapplication.model.*;
import com.kienast.examapplication.repository.GivenAnswerRepository;
import com.kienast.examapplication.repository.TestRepository;
import com.kienast.examapplication.repository.TestResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final GivenAnswerRepository givenAnswerRepository;
    private final AuthService authService;
    private final QuestionService questionService;
    private final AnswerService answerService;


    @Autowired
    public TestService(TestRepository testRepository,
                       TestResultRepository testResultRepository,
                       GivenAnswerRepository givenAnswRepository, AuthService authService,
                       QuestionService questionService,
                       AnswerService answerService) {
        this.givenAnswerRepository = givenAnswRepository;

        LOG.info("TestService: constructor called");
        this.testRepository = testRepository;
        this.testResultRepository = testResultRepository;
        this.authService = authService;
        this.questionService = questionService;
        this.answerService = answerService;
    }


    public List<Test> getAllTests() {
        LOG.info("TestService: getAllTests possibleAnswers");
        List<Test> testList = this.testRepository.findAll();
        LOG.info("TestService: getAllTests testList -> {}", testList);
        return testList;
    }


    public Test getTestById(Long testId) {
        LOG.info("TestService: getTestById testId -> {}", testId);
        Optional<Test> optionalTest = this.testRepository.findById(testId);

        if (optionalTest.isPresent()) {
            ReceiveTestDto receiveTestDto = new ReceiveTestDto();
            //receiveTestDto.setId(testId);
            Test test = optionalTest.get();
            LOG.info("TestService: getTestById found test -> {}", test);
            return test;
        }

        LOG.info("TestService: getTestById did not find test with id -> {}", testId);
        return null;
    }


    private List<TestResult> getTestResultsForTest(Test test) {
        LOG.info("TestService: getTestResultsForTest test -> {}", test);
        List<TestResult> testResults = this.testResultRepository.findTestResultsByTest(test);
        LOG.info("TestService: testResults -> {}", testResults);
        return testResults;
    }


    public String createNewTest(CreateTestDto2 createTestDto) {
        LOG.info("TestService: createNewTest createTestDto -> {}", createTestDto);
        User givenUser = new User();
        givenUser.setUsername(createTestDto.getUserDto().getUsername());
        givenUser.setPassword(createTestDto.getUserDto().getPassword());
        LOG.info("TestService: createNewTest givenUser -> {}", givenUser);

        User user = this.authService.login(givenUser);
        LOG.info("TestService: createNewTest user -> {}", user);

        if (user == null) {
            LOG.info("TestService: createNewTest did not find user");
            return "Test creation failed, because user credentials not valid.";
        }

        Test test = new Test();
        test.setTestName(createTestDto.getTestName());
        test.setCreatedBy(user);
        test.setCreatedAt(new Date());
        LOG.info("TestService: createNewTest unsaved test -> {}", test);
        this.testRepository.save(test);
        LOG.info("TestService: createNewTest saved test -> {}", test);

        List<CreateTestQuestionDto> questionDtos = createTestDto.getQuestionList();
        List<Question> questions = new ArrayList<>();
        for (CreateTestQuestionDto questionDto : questionDtos) {
            Question question = new Question();
            question.setTest(test);
            List<CreateTestPossibleAnswerDto> possibleAnswerDtos = questionDto.getPossibleAnswers();
            List<PossibleAnswer> possibleAnswers = new ArrayList<>();
            for (CreateTestPossibleAnswerDto possibleAnswerDto : possibleAnswerDtos) {
                PossibleAnswer possibleAnswer = new PossibleAnswer();
                possibleAnswer.setAnswer(possibleAnswerDto.getAnswer());
                possibleAnswer.setCorrect(possibleAnswerDto.isCorrect());
                possibleAnswers.add(possibleAnswer);
            }
            question.setPossibleAnswers(possibleAnswers);
            question.setQuestionName(questionDto.getQuestionName());
            questions.add(question);
        }

        this.questionService.saveQuestions(questions);

        return "Test creation successful!";
    }


    public String sendTestWithAnswers(SendTestResultDto sendTestResultDto) {
        User givenUser = new User();
        givenUser.setUsername(sendTestResultDto.getUserDto().getUsername());
        givenUser.setPassword(sendTestResultDto.getUserDto().getPassword());

        User user = this.authService.login(givenUser);

        if (user == null) {
            return "Test sending failed, because user not found.";
        }

        Test test = getTestById(sendTestResultDto.getTestDto().getTestId());

        TestResult testResult = new TestResult();
        testResult.setResult(sendTestResultDto.getResult());
        testResult.setTest(test);
        testResult.setCreatedBy(user);
        testResult.setCreatedAt(new Date());
        this.testResultRepository.save(testResult);

        List<SendTestResultGivenAnswerDto> givenAnswerDtos = sendTestResultDto.getGivenAnswers();
        List<GivenAnswer> givenAnswers = new ArrayList<>();
        for (SendTestResultGivenAnswerDto givenAnswerDto : givenAnswerDtos) {
            GivenAnswer givenAnswer = new GivenAnswer();
            givenAnswer.setTestResult(testResult);

            PossibleAnswer possibleAnswer = new PossibleAnswer();
            possibleAnswer.setPossibleAnswerId(givenAnswerDto.getPossibleAnswerDto().getPossibleAnswerId());
            givenAnswer.setAnswer(possibleAnswer);

            givenAnswers.add(givenAnswer);
        }

        this.answerService.saveGivenAnswers(givenAnswers);

        return "Test sending successful!";
    }


}
