package com.kienast.examapplication.controller;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.Test;
import com.kienast.examapplication.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> testList = this.testService.getAllTests();
        return new ResponseEntity<List<Test>>(testList, HttpStatus.OK);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable String testId) {
        Test test = this.testService.getTestById(testId);
        return new ResponseEntity<Test>(test, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Test> createNewTest(@RequestBody Test givenTest) {
        Test test = this.testService.createNewTest(givenTest);
        return new ResponseEntity<Test>(test, HttpStatus.CREATED);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendTestWithAnswers(@RequestBody List<GivenAnswer> givenAnswers) {
        String response = this.testService.sendTestWithAnswers(givenAnswers);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
