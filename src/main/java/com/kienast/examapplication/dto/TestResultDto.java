package com.kienast.examapplication.dto;

import java.util.List;
import java.util.Objects;

public class TestResultDto {

    private Long testResultId;
    private Double result;
    private UserDto userDto;
    private List<GivenAnswerDto> givenAnswerDtos;

    public TestResultDto() {
    }

    public TestResultDto(Long testResultId, Double result, UserDto userDto, List<GivenAnswerDto> givenAnswerDtos) {
        this.testResultId = testResultId;
        this.result = result;
        this.userDto = userDto;
        this.givenAnswerDtos = givenAnswerDtos;
    }

    public Long getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Long testResultId) {
        this.testResultId = testResultId;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<GivenAnswerDto> getGivenAnswerDtos() {
        return givenAnswerDtos;
    }

    public void setGivenAnswerDtos(List<GivenAnswerDto> givenAnswerDtos) {
        this.givenAnswerDtos = givenAnswerDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResultDto that = (TestResultDto) o;
        return Objects.equals(testResultId, that.testResultId) && Objects.equals(result, that.result) && Objects.equals(userDto, that.userDto) && Objects.equals(givenAnswerDtos, that.givenAnswerDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testResultId, result, userDto, givenAnswerDtos);
    }

    @Override
    public String toString() {
        return "TestResultDto{" +
                "testResultId=" + testResultId +
                ", result=" + result +
                ", userDto=" + userDto +
                ", givenAnswerDtos=" + givenAnswerDtos +
                '}';
    }
}
