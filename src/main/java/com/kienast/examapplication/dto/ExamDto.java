package com.kienast.examapplication.dto;

import java.util.Objects;

public class ExamDto {

    private Long examId;
    private String examName;
    private UserDto userDto;

    public ExamDto() {
    }

    public ExamDto(Long examId, String examName, UserDto userDto) {
        this.examId = examId;
        this.examName = examName;
        this.userDto = userDto;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDto examDto = (ExamDto) o;
        return Objects.equals(examId, examDto.examId) && Objects.equals(examName, examDto.examName) && Objects.equals(userDto, examDto.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, examName, userDto);
    }

    @Override
    public String toString() {
        return "ExamDto{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", userDto=" + userDto +
                '}';
    }
}
