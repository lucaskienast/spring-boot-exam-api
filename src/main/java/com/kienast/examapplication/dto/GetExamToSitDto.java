package com.kienast.examapplication.dto;

import java.util.List;
import java.util.Objects;

public class GetExamToSitDto {

    private ExamDto examDto;
    private List<QuestionDto> questions;

    public GetExamToSitDto() {
    }

    public GetExamToSitDto(ExamDto examDto, List<QuestionDto> questions) {
        this.examDto = examDto;
        this.questions = questions;
    }

    public ExamDto getExamDto() {
        return examDto;
    }

    public void setExamDto(ExamDto examDto) {
        this.examDto = examDto;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetExamToSitDto that = (GetExamToSitDto) o;
        return Objects.equals(examDto, that.examDto) && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examDto, questions);
    }

    @Override
    public String toString() {
        return "GetExamToSitDto{" +
                "examDto=" + examDto +
                ", questions=" + questions +
                '}';
    }
}
