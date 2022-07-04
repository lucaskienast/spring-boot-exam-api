package com.kienast.examapplication.dto;

import java.util.Objects;

public class GivenAnswerDto {

    private Long givenAnswerId;
    private PossibleAnswerDto possibleAnswer;

    public GivenAnswerDto() {
    }

    public GivenAnswerDto(Long givenAnswerId, PossibleAnswerDto possibleAnswer) {
        this.givenAnswerId = givenAnswerId;
        this.possibleAnswer = possibleAnswer;
    }

    public Long getGivenAnswerId() {
        return givenAnswerId;
    }

    public void setGivenAnswerId(Long givenAnswerId) {
        this.givenAnswerId = givenAnswerId;
    }

    public PossibleAnswerDto getPossibleAnswer() {
        return possibleAnswer;
    }

    public void setPossibleAnswer(PossibleAnswerDto possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GivenAnswerDto that = (GivenAnswerDto) o;
        return Objects.equals(givenAnswerId, that.givenAnswerId) && Objects.equals(possibleAnswer, that.possibleAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenAnswerId, possibleAnswer);
    }

    @Override
    public String toString() {
        return "GivenAnswerDto{" +
                "givenAnswerId=" + givenAnswerId +
                ", possibleAnswer=" + possibleAnswer +
                '}';
    }
}
