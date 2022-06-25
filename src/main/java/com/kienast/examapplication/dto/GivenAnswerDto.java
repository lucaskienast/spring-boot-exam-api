package com.kienast.examapplication.dto;

import java.util.Objects;

public class GivenAnswerDto {

    private Long givenAnswerId;
    private PossibleAnswerDto possibleAnswerDto;

    public GivenAnswerDto() {
    }

    public GivenAnswerDto(Long givenAnswerId, PossibleAnswerDto possibleAnswerDto) {
        this.givenAnswerId = givenAnswerId;
        this.possibleAnswerDto = possibleAnswerDto;
    }

    public Long getGivenAnswerId() {
        return givenAnswerId;
    }

    public void setGivenAnswerId(Long givenAnswerId) {
        this.givenAnswerId = givenAnswerId;
    }

    public PossibleAnswerDto getPossibleAnswerDto() {
        return possibleAnswerDto;
    }

    public void setPossibleAnswerDto(PossibleAnswerDto possibleAnswerDto) {
        this.possibleAnswerDto = possibleAnswerDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GivenAnswerDto that = (GivenAnswerDto) o;
        return Objects.equals(givenAnswerId, that.givenAnswerId) && Objects.equals(possibleAnswerDto, that.possibleAnswerDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenAnswerId, possibleAnswerDto);
    }

    @Override
    public String toString() {
        return "GivenAnswerDto{" +
                "givenAnswerId=" + givenAnswerId +
                ", possibleAnswerDto=" + possibleAnswerDto +
                '}';
    }
}
