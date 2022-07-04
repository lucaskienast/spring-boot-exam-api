package com.kienast.examapplication.dto;

import java.util.Objects;

public class CreateTestPossibleAnswerDto {

    private String answer;
    private boolean correct;

    public CreateTestPossibleAnswerDto() {
    }

    public CreateTestPossibleAnswerDto(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTestPossibleAnswerDto that = (CreateTestPossibleAnswerDto) o;
        return correct == that.correct && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, correct);
    }

    @Override
    public String toString() {
        return "CreateTestPossibleAnswerDto{" +
                "answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }
}
