package com.kienast.examapplication.dto;


import java.util.List;
import java.util.Objects;

public class CreateTestDto2 {

    private String testName;
    private UserDto userDto;
    private List<CreateTestQuestionDto> questionList;

    public CreateTestDto2() {
    }

    public CreateTestDto2(String testName, UserDto userDto, List<CreateTestQuestionDto> questionList) {
        this.testName = testName;
        this.userDto = userDto;
        this.questionList = questionList;
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

    public List<CreateTestQuestionDto> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<CreateTestQuestionDto> questionList) {
        this.questionList = questionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTestDto2 that = (CreateTestDto2) o;
        return Objects.equals(testName, that.testName) && Objects.equals(userDto, that.userDto) && Objects.equals(questionList, that.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, userDto, questionList);
    }

    @Override
    public String toString() {
        return "CreateTestDto2{" +
                "testName='" + testName + '\'' +
                ", userDto=" + userDto +
                ", questionList=" + questionList +
                '}';
    }
}
