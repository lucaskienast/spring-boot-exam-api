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
        LOG.info("TestService: getAllTests");
        List<Test> testList = this.testRepository.findAll();
        LOG.info("TestService: getAllTests testList -> {}", testList);
        return testList;
    }


    public GetExamToSitDto getExamToSitById(Long testId) {
        LOG.info("TestService: getTestById testId -> {}", testId);
        Optional<Test> optionalTest = this.testRepository.findById(testId);

        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            LOG.info("TestService: getTestById found test -> {}", test);

            UserDto userDto = new UserDto();
            userDto.setUsername(test.getCreatedBy().getUsername());

            ExamDto examDto = new ExamDto();
            examDto.setExamId(test.getTestId());
            examDto.setExamName(test.getTestName());
            examDto.setUserDto(userDto);

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

            GetExamToSitDto getExamToSitDto = new GetExamToSitDto();
            getExamToSitDto.setExamDto(examDto);
            getExamToSitDto.setQuestions(questionDtos);

            return getExamToSitDto;
        }

        LOG.info("TestService: getTestById did not find test with id -> {}", testId);
        return null;
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


    public List<WholeTestDto> getAllWholeExams() {
        LOG.info("TestService: getAllWholeExams");
        List<Test> testList = getAllTests();
        List<WholeTestDto> wholeExams = new ArrayList<>();

        for (Test test : testList) {
            wholeExams.add(getTestWithQuestionsAndResultsById(test.getTestId()));
        }

        return wholeExams;
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
                givenAnswerDto.setPossibleAnswer(possibleAnswerDto);

                givenAnswerDtos.add(givenAnswerDto);
            }

            UserDto testResultUser = new UserDto();
            testResultUser.setUsername(testResult.getCreatedBy().getUsername());

            TestResultDto testResultDto = new TestResultDto();
            testResultDto.setTestResultId(testResult.getTestResultId());
            testResultDto.setResult(testResult.getResult());
            testResultDto.setGivenAnswerDtos(givenAnswerDtos);
            testResultDto.setUserDto(testResultUser);

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


    public Test createNewTest(CreateTestDto2 createTestDto) {
        LOG.info("TestService: createNewTest createTestDto -> {}", createTestDto);
        User givenUser = new User();
        givenUser.setUsername(createTestDto.getUserDto().getUsername());
        givenUser.setPassword(createTestDto.getUserDto().getPassword());
        LOG.info("TestService: createNewTest givenUser -> {}", givenUser);

        User user = this.authService.login(givenUser);
        LOG.info("TestService: createNewTest user -> {}", user);

        if (user == null) {
            LOG.warn("TestService: createNewTest did not find user");
            LOG.info("TestService: createNewTest sign up user");
            user = this.authService.signup(givenUser);

            if (user == null) {
                LOG.error("TestService: createNewTest did not sign up user");
            }
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

        return test;
    }


    public TestResultDto sendTestWithAnswers(SendTestResultDto sendTestResultDto) {
        User givenUser = new User();
        givenUser.setUsername(sendTestResultDto.getUserDto().getUsername());
        givenUser.setPassword(sendTestResultDto.getUserDto().getPassword());
        LOG.info("TestService: sendTestWithAnswers givenUser -> {}", givenUser);

        User user = this.authService.login(givenUser);
        LOG.info("TestService: sendTestWithAnswers logged in user -> {}", user);

        if (user == null) {
            LOG.warn("TestService: sendTestWithAnswers did not find user");
            LOG.info("TestService: sendTestWithAnswers sign up user");
            user = this.authService.signup(givenUser);

            if (user == null) {
                LOG.error("TestService: sendTestWithAnswers did not sign up user");
            }
        }

        Test test = getTestById(sendTestResultDto.getExamDto().getExamId());

        TestResult testResult = new TestResult();
        testResult.setResult(sendTestResultDto.getResult());
        testResult.setTest(test);
        testResult.setCreatedBy(user);
        testResult.setCreatedAt(new Date());
        this.testResultRepository.save(testResult);

        List<GivenAnswerDto> givenAnswerDtos = sendTestResultDto.getGivenAnswers();
        List<GivenAnswer> givenAnswers = new ArrayList<>();
        for (GivenAnswerDto givenAnswerDto : givenAnswerDtos) {
            PossibleAnswer possibleAnswer = this.answerService.getPossibleAnswerById(givenAnswerDto.getPossibleAnswer().getPossibleAnswerId());

            GivenAnswer givenAnswer = new GivenAnswer();
            givenAnswer.setTestResult(testResult);
            givenAnswer.setAnswer(possibleAnswer);
            givenAnswers.add(givenAnswer);
        }

        this.answerService.saveGivenAnswers(givenAnswers);

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());

        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setTestResultId(testResult.getTestResultId());
        testResultDto.setResult(testResult.getResult());
        testResultDto.setUserDto(userDto);

        return testResultDto;
    }


    public TestResultDto getExamResultById(Long examResultId) {
        Optional<TestResult> optionalTestResult = this.testResultRepository.findById(examResultId);

        if (optionalTestResult.isPresent()) {
            TestResult testResult = optionalTestResult.get();
            List<GivenAnswer> givenAnswers = testResult.getGivenAnswers();
            List<GivenAnswerDto> givenAnswerDtos = new ArrayList<>();

            for (GivenAnswer givenAnswer : givenAnswers) {
                PossibleAnswerDto possibleAnswerDto = new PossibleAnswerDto();
                possibleAnswerDto.setAnswer(givenAnswer.getAnswer().getAnswer());
                possibleAnswerDto.setCorrect(givenAnswer.getAnswer().isCorrect());

                GivenAnswerDto givenAnswerDto = new GivenAnswerDto();
                givenAnswerDto.setPossibleAnswer(possibleAnswerDto);

                givenAnswerDtos.add(givenAnswerDto);
            }

            TestResultDto testResultDto = new TestResultDto();
            testResultDto.setTestResultId(testResult.getTestResultId());
            testResultDto.setResult(testResult.getResult());
            testResultDto.setGivenAnswerDtos(givenAnswerDtos);

            UserDto userDto = new UserDto();
            userDto.setUserId(testResult.getCreatedBy().getUserId());
            userDto.setUsername(testResult.getCreatedBy().getUsername());

            testResultDto.setUserDto(userDto);

            return testResultDto;
        }

        return null;
    }


}
