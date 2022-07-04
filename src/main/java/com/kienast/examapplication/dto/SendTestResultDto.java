package com.kienast.examapplication.dto;

import java.util.List;
import java.util.Objects;

public class SendTestResultDto {

    private Double result;
    private UserDto userDto;
    private TestDto examDto;
    private List<GivenAnswerDto> givenAnswers;

    public SendTestResultDto() {
    }

    public SendTestResultDto(Double result, UserDto userDto, TestDto examDto, List<GivenAnswerDto> givenAnswers) {
        this.result = result;
        this.userDto = userDto;
        this.examDto = examDto;
        this.givenAnswers = givenAnswers;
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

    public TestDto getExamDto() {
        return examDto;
    }

    public void setExamDto(TestDto examDto) {
        this.examDto = examDto;
    }

    public List<GivenAnswerDto> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<GivenAnswerDto> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendTestResultDto that = (SendTestResultDto) o;
        return Objects.equals(result, that.result) && Objects.equals(userDto, that.userDto) && Objects.equals(examDto, that.examDto) && Objects.equals(givenAnswers, that.givenAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, userDto, examDto, givenAnswers);
    }

    @Override
    public String toString() {
        return "SendTestResultDto{" +
                "result=" + result +
                ", userDto=" + userDto +
                ", examDto=" + examDto +
                ", givenAnswers=" + givenAnswers +
                '}';
    }
}
