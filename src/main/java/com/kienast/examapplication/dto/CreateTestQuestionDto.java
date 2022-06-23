package com.kienast.examapplication.dto;


import java.util.List;
import java.util.Objects;

public class CreateTestQuestionDto {

    private String questionName;
    private List<CreateTestPossibleAnswerDto> possibleAnswers;

    public CreateTestQuestionDto() {
    }

    public CreateTestQuestionDto(String questionName, List<CreateTestPossibleAnswerDto> possibleAnswers) {
        this.questionName = questionName;
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<CreateTestPossibleAnswerDto> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<CreateTestPossibleAnswerDto> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTestQuestionDto that = (CreateTestQuestionDto) o;
        return Objects.equals(questionName, that.questionName) && Objects.equals(possibleAnswers, that.possibleAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionName, possibleAnswers);
    }

    @Override
    public String toString() {
        return "CreateTestQuestionDto{" +
                "questionName='" + questionName + '\'' +
                ", possibleAnswers=" + possibleAnswers +
                '}';
    }
}
