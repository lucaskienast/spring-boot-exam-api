package com.kienast.examapplication.dto;

import java.util.List;
import java.util.Objects;

public class SendTestResultDto {

    private Double result;
    private UserDto userDto;
    private TestDto testDto;
    private List<SendTestResultGivenAnswerDto> givenAnswers;

    public SendTestResultDto() {
    }

    public SendTestResultDto(Double result, UserDto userDto, TestDto testDto, List<SendTestResultGivenAnswerDto> givenAnswers) {
        this.result = result;
        this.userDto = userDto;
        this.testDto = testDto;
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

    public TestDto getTestDto() {
        return testDto;
    }

    public void setTestDto(TestDto testDto) {
        this.testDto = testDto;
    }

    public List<SendTestResultGivenAnswerDto> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<SendTestResultGivenAnswerDto> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendTestResultDto that = (SendTestResultDto) o;
        return Objects.equals(result, that.result) && Objects.equals(userDto, that.userDto) && Objects.equals(testDto, that.testDto) && Objects.equals(givenAnswers, that.givenAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, userDto, testDto, givenAnswers);
    }

    @Override
    public String toString() {
        return "SendTestResultDto{" +
                "result=" + result +
                ", userDto=" + userDto +
                ", testDto=" + testDto +
                ", givenAnswers=" + givenAnswers +
                '}';
    }
}
