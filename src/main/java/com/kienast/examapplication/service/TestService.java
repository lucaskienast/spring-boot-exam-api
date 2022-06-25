package com.kienast.examapplication.service;

import com.kienast.examapplication.dto.*;
import com.kienast.examapplication.model.*;
import com.kienast.examapplication.repository.GivenAnswerRepository;
import com.kienast.examapplication.repository.PossibleAnswerRepository;
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
    private final PossibleAnswerRepository possibleAnswerRepository;
    private final AuthService authService;
    private final QuestionService questionService;
    private final AnswerService answerService;


    @Autowired
    public TestService(TestRepository testRepository,
                       TestResultRepository testResultRepository,
                       GivenAnswerRepository givenAnswerRepository, PossibleAnswerRepository possibleAnswerRepository, AuthService authService,
                       QuestionService questionService,
                       AnswerService answerService) {
        this.givenAnswerRepository = givenAnswerRepository;
        this.possibleAnswerRepository = possibleAnswerRepository;

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
            Test test = optionalTest.get();
            LOG.info("TestService: getTestById found test -> {}", test);
            return test;
        }

        LOG.info("TestService: getTestById did not find test with id -> {}", testId);
        return null;
    }


    public WholeTestDto getTestWithQuestionsAndResultsById(Long testId) {
        Test test = getTestById(testId);

        UserDto userDto = new UserDto();
        userDto.setUserId(test.getCreatedBy().getUserId());
        userDto.setUsername(test.getCreatedBy().getUsername());
        userDto.setEmail(test.getCreatedBy().getEmail());

        List<Question> questions = questionService.getAllQuestionsByTest(test);
        List<QuestionDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            List<PossibleAnswer> possibleAnswers = this.possibleAnswerRepository.findAllByQuestion(question);
            List<PossibleAnswerDto> possibleAnswerDtos = new ArrayList<>();

            for (PossibleAnswer possibleAnswer : possibleAnswers) {
                PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                possibleAnswerDto.setPossibleAnswerId(possibleAnswer.getPossibleAnswerId());
                possibleAnswerDto.setAnswer(possibleAnswer.getAnswer());
                possibleAnswerDto.setCorrect(possibleAnswer.isCorrect());
                possibleAnswerDtos.add(possibleAnswerDto);
            }

            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestionId(question.getQuestionId());
            questionDto.setQuestionName(question.getQuestionName());
            questionDto.setPossibleAnswers(possibleAnswerDtos);
            questionDtos.add(questionDto);
        }

        List<TestResult> testResults = getTestResultsForTest(test);
        List<TestResultDto> testResultDtos = new ArrayList<>();

        for (TestResult testResult : testResults) {
            List<GivenAnswer> givenAnswers = this.givenAnswerRepository.findGivenAnswerByTestResult(testResult);
            List<GivenAnswerDto> givenAnswerDtos = new ArrayList<>();

            for (GivenAnswer givenAnswer : givenAnswers) {

                PossibleAnswer possibleAnswer = givenAnswer.getAnswer();
                PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                possibleAnswerDto.setPossibleAnswerId(possibleAnswer.getPossibleAnswerId());
                possibleAnswerDto.setAnswer(possibleAnswer.getAnswer());
                possibleAnswerDto.setCorrect(possibleAnswer.isCorrect());

                GivenAnswerDto givenAnswerDto = new GivenAnswerDto();
                givenAnswerDto.setGivenAnswerId(givenAnswer.getGivenAnswerId());
                givenAnswerDto.setPossibleAnswerDto(possibleAnswerDto);

                givenAnswerDtos.add(givenAnswerDto);
            }

            TestResultDto testResultDto = new TestResultDto();
            testResultDto.setTestResultId(testResult.getTestResultId());
            testResultDto.setResult(testResult.getResult());
            testResultDto.setGivenAnswerDtos(givenAnswerDtos);
            testResultDto.setUserDto(userDto);

            testResultDtos.add(testResultDto);
        }

        WholeTestDto wholeTest = new WholeTestDto();
        wholeTest.setTestId(test.getTestId());
        wholeTest.setTestName(test.getTestName());
        wholeTest.setUserDto(userDto);
        wholeTest.setCreatedAt(test.getCreatedAt());
        wholeTest.setQuestionDtos(questionDtos);
        wholeTest.setTestResultDtos(testResultDtos);

        return wholeTest;
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
            return "401 Unauthorized";
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

        return "201 CREATED";
    }


    public String sendTestWithAnswers(SendTestResultDto sendTestResultDto) {
        User givenUser = new User();
        givenUser.setUsername(sendTestResultDto.getUserDto().getUsername());
        givenUser.setPassword(sendTestResultDto.getUserDto().getPassword());

        User user = this.authService.login(givenUser);

        if (user == null) {
            return "401 Unauthorized";
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

        return "201 CREATED";
    }


}
