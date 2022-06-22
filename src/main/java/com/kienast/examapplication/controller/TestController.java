package com.kienast.examapplication.controller;

import com.kienast.examapplication.dto.CreateTestDto;
import com.kienast.examapplication.dto.SendTestDto;
import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.Test;
import com.kienast.examapplication.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    // setup logging

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }


    // return tests with all attributes from EAGER loading
    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> testList = this.testService.getAllTests();
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }


    // return test with all attributes from EAGER loading
    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable String testId) {
        Test test = this.testService.getTestById(Long.parseLong(testId));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createNewTest(@RequestBody CreateTestDto createTestDto) {
        String response = this.testService.createNewTest(createTestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /** in progress */
    @PostMapping("/send")
    public ResponseEntity<String> sendTestWithAnswers(@RequestBody SendTestDto sendTestDto) {
        String response = this.testService.sendTestWithAnswers(sendTestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
