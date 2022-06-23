package com.kienast.examapplication.dto;


import java.util.Date;
import java.util.List;

public class ReceiveTestDto {

    private Long testId;
    private String testName;
    private UserDto userDto;
    private List<QuestionDto> questionDtos;
    private Date createdAt;
    private List<TestResultDto> testResultDtos;

}
