package com.kienast.examapplication.controller;

import com.kienast.examapplication.dto.*;
import com.kienast.examapplication.model.Test;
import com.kienast.examapplication.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private final TestService testService;


    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        LOG.info("TestController: getAllTests");
        List<Test> testList = this.testService.getAllTests();
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }


    @GetMapping("/allWholeTest")
    public ResponseEntity<List<WholeTestDto>> getAllWholeExams() {
        LOG.info("TestController: getAllWholeExams");
        List<WholeTestDto> testList = this.testService.getAllWholeExams();
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }


    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable String testId) {
        LOG.info("TestController: getTestById testId -> {}", testId);
        Test test = this.testService.getTestById(Long.parseLong(testId));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }


    @GetMapping("/wholeTest/{testId}")
    public ResponseEntity<WholeTestDto> getWholeTestById(@PathVariable String testId) {
        LOG.info("TestController: getWholeTestById testId -> {}", testId);
        WholeTestDto wholeTest = this.testService.getTestWithQuestionsAndResultsById(Long.parseLong(testId));
        return new ResponseEntity<>(wholeTest, HttpStatus.OK);
    }


    @GetMapping("/sit/{testId}")
    public ResponseEntity<GetExamToSitDto> getExamToSitById(@PathVariable String testId) {
        LOG.info("TestController: getExamToSitById testId -> {}", testId);
        GetExamToSitDto getExamToSitDto = this.testService.getExamToSitById(Long.parseLong(testId));
        return new ResponseEntity<>(getExamToSitDto, HttpStatus.OK);
    }


    @GetMapping("/exam-result/{examId}")
    public ResponseEntity<TestResultDto> getExamResultById(@PathVariable String examId) {
        LOG.info("TestController: getExamResultById examResultId -> {}", examId);
        TestResultDto examResultDto = this.testService.getExamResultById(Long.parseLong(examId));
        return new ResponseEntity<>(examResultDto, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Test> createNewTest(@RequestBody CreateTestDto2 createTestDto) {
        LOG.info("TestController: createNewTest createTestDto -> {}", createTestDto);
        Test response = this.testService.createNewTest(createTestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/send")
    public ResponseEntity<TestResultDto> sendTestWithAnswers(@RequestBody SendTestResultDto sendTestResultDto) {
        LOG.info("TestController: sendTestWithAnswers sendTestResultDto -> {}", sendTestResultDto);
        TestResultDto response = this.testService.sendTestWithAnswers(sendTestResultDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
