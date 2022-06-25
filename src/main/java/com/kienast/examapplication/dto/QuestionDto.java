package com.kienast.examapplication.dto;

import java.util.List;
import java.util.Objects;

public class QuestionDto {

    private Long questionId;
    private String questionName;
    private List<PossibleAnswerDto> possibleAnswers;

    public QuestionDto() {
    }

    public QuestionDto(Long questionId, String questionName, List<PossibleAnswerDto> possibleAnswers) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.possibleAnswers = possibleAnswers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<PossibleAnswerDto> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PossibleAnswerDto> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(questionName, that.questionName) && Objects.equals(possibleAnswers, that.possibleAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionName, possibleAnswers);
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "questionId=" + questionId +
                ", questionName='" + questionName + '\'' +
                ", possibleAnswers=" + possibleAnswers +
                '}';
    }
}
