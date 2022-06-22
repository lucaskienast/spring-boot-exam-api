package com.kienast.examapplication.dto;

import com.kienast.examapplication.model.GivenAnswer;
import com.kienast.examapplication.model.TestResult;
import com.kienast.examapplication.model.User;

import java.util.List;
import java.util.Objects;

public class SendTestDto {

    private TestResult testResult;
    private List<GivenAnswer> givenAnswerList;
    private User user;

    public SendTestDto() {
    }

    public SendTestDto(TestResult testResult, List<GivenAnswer> givenAnswerList, User user) {
        this.testResult = testResult;
        this.givenAnswerList = givenAnswerList;
        this.user = user;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public List<GivenAnswer> getGivenAnswerList() {
        return givenAnswerList;
    }

    public void setGivenAnswerList(List<GivenAnswer> givenAnswerList) {
        this.givenAnswerList = givenAnswerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendTestDto that = (SendTestDto) o;
        return Objects.equals(testResult, that.testResult) && Objects.equals(givenAnswerList, that.givenAnswerList) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testResult, givenAnswerList, user);
    }

    @Override
    public String toString() {
        return "SendTestDto{" +
                "testResult=" + testResult +
                ", givenAnswerList=" + givenAnswerList +
                ", user=" + user +
                '}';
    }
}
