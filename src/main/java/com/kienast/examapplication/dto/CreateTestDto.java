package com.kienast.examapplication.dto;

import com.kienast.examapplication.model.Test;
import com.kienast.examapplication.model.User;
import java.util.Objects;


public class CreateTestDto {

    private Test test;
    private User user;

    public CreateTestDto() {
    }

    public CreateTestDto(Test test, User user) {
        this.test = test;
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        CreateTestDto that = (CreateTestDto) o;
        return Objects.equals(test, that.test) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, user);
    }

    @Override
    public String toString() {
        return "CreateTestDto{" +
                "test=" + test +
                ", user=" + user +
                '}';
    }
}
