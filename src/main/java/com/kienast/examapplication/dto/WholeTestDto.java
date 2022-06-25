package com.kienast.examapplication.dto;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WholeTestDto {

    private Long testId;
    private String testName;
    private UserDto userDto;
    private List<QuestionDto> questionDtos;
    private Date createdAt;
    private List<TestResultDto> testResultDtos;

    public WholeTestDto() {
    }

    public WholeTestDto(Long testId, String testName, UserDto userDto, List<QuestionDto> questionDtos, Date createdAt, List<TestResultDto> testResultDtos) {
        this.testId = testId;
        this.testName = testName;
        this.userDto = userDto;
        this.questionDtos = questionDtos;
        this.createdAt = createdAt;
        this.testResultDtos = testResultDtos;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<TestResultDto> getTestResultDtos() {
        return testResultDtos;
    }

    public void setTestResultDtos(List<TestResultDto> testResultDtos) {
        this.testResultDtos = testResultDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WholeTestDto that = (WholeTestDto) o;
        return Objects.equals(testId, that.testId) && Objects.equals(testName, that.testName) && Objects.equals(userDto, that.userDto) && Objects.equals(questionDtos, that.questionDtos) && Objects.equals(createdAt, that.createdAt) && Objects.equals(testResultDtos, that.testResultDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, testName, userDto, questionDtos, createdAt, testResultDtos);
    }

    @Override
    public String toString() {
        return "WholeTestDto{" +
                "testId=" + testId +
                ", testName='" + testName + '\'' +
                ", userDto=" + userDto +
                ", questionDtos=" + questionDtos +
                ", createdAt=" + createdAt +
                ", testResultDtos=" + testResultDtos +
                '}';
    }
}
