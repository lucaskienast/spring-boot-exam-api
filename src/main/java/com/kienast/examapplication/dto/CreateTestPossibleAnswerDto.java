package com.kienast.examapplication.dto;

import java.util.Objects;

public class CreateTestPossibleAnswerDto {

    private String answer;
    private boolean isCorrect;

    public CreateTestPossibleAnswerDto() {
    }

    public CreateTestPossibleAnswerDto(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
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
        CreateTestPossibleAnswerDto that = (CreateTestPossibleAnswerDto) o;
        return isCorrect == that.isCorrect && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, isCorrect);
    }

    @Override
    public String toString() {
        return "CreateTestPossibleAnswerDto{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
