package com.kienast.examapplication.dto;

import java.util.Objects;

public class PossibleAnswerDto {

    private Long possibleAnswerId;
    private String answer;
    private boolean isCorrect;

    public PossibleAnswerDto() {
    }

    public PossibleAnswerDto(Long possibleAnswerId, String answer, boolean isCorrect) {
        this.possibleAnswerId = possibleAnswerId;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public Long getPossibleAnswerId() {
        return possibleAnswerId;
    }

    public void setPossibleAnswerId(Long possibleAnswerId) {
        this.possibleAnswerId = possibleAnswerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PossibleAnswerDto that = (PossibleAnswerDto) o;
        return isCorrect == that.isCorrect && Objects.equals(possibleAnswerId, that.possibleAnswerId) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleAnswerId, answer, isCorrect);
    }

    @Override
    public String toString() {
        return "PossibleAnswerDto{" +
                "possibleAnswerId=" + possibleAnswerId +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
