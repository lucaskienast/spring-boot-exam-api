package com.kienast.examapplication.controller;

import com.kienast.examapplication.dto.CreateTestDto2;
import com.kienast.examapplication.dto.SendTestResultDto;
import com.kienast.examapplication.dto.WholeTestDto;
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


    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable String testId) {
        LOG.info("TestController: getTestById testId -> {}", testId);
        Test test = this.testService.getTestById(Long.parseLong(testId));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }


    @GetMapping("/wholeTest/{testId}")
    public ResponseEntity<WholeTestDto> getWholeTestById(@PathVariable String testId) {
        LOG.info("TestController: getTestById testId -> {}", testId);
        WholeTestDto wholeTest = this.testService.getTestWithQuestionsAndResultsById(Long.parseLong(testId));
        return new ResponseEntity<>(wholeTest, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createNewTest(@RequestBody CreateTestDto2 createTestDto) {
        LOG.info("TestController: createNewTest createTestDto -> {}", createTestDto);
        String response = this.testService.createNewTest(createTestDto);
        if (response.equals("201 CREATED")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.equals("401 Unauthorized")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendTestWithAnswers(@RequestBody SendTestResultDto sendTestResultDto) {
        LOG.info("TestController: sendTestWithAnswers sendTestResultDto -> {}", sendTestResultDto);
        String response = this.testService.sendTestWithAnswers(sendTestResultDto);
        if (response.equals("201 CREATED")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.equals("401 Unauthorized")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}
