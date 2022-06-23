package com.kienast.examapplication.dto;

import java.util.Objects;

public class SendTestResultGivenAnswerDto {

    private PossibleAnswerDto possibleAnswerDto;
    private TestDto testDto;

    public SendTestResultGivenAnswerDto() {
    }

    public SendTestResultGivenAnswerDto(PossibleAnswerDto possibleAnswerDto, TestDto testDto) {
        this.possibleAnswerDto = possibleAnswerDto;
        this.testDto = testDto;
    }

    public PossibleAnswerDto getPossibleAnswerDto() {
        return possibleAnswerDto;
    }

    public void setPossibleAnswerDto(PossibleAnswerDto possibleAnswerDto) {
        this.possibleAnswerDto = possibleAnswerDto;
    }

    public TestDto getTestDto() {
        return testDto;
    }

    public void setTestDto(TestDto testDto) {
        this.testDto = testDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendTestResultGivenAnswerDto that = (SendTestResultGivenAnswerDto) o;
        return Objects.equals(possibleAnswerDto, that.possibleAnswerDto) && Objects.equals(testDto, that.testDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleAnswerDto, testDto);
    }

    @Override
    public String toString() {
        return "SendTestResultGivenAnswerDto{" +
                "possibleAnswerDto=" + possibleAnswerDto +
                ", testDto=" + testDto +
                '}';
    }
}
