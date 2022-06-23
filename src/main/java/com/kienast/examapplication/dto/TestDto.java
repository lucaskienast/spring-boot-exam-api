package com.kienast.examapplication.dto;

import java.util.Objects;

public class TestDto {

    private Long testId;
    private String testName;
    private UserDto userDto;

    public TestDto() {
    }

    public TestDto(Long testId, String testName, UserDto userDto) {
        this.testId = testId;
        this.testName = testName;
        this.userDto = userDto;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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
        TestDto testDto = (TestDto) o;
        return Objects.equals(testId, testDto.testId) && Objects.equals(testName, testDto.testName) && Objects.equals(userDto, testDto.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, testName, userDto);
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "testId='" + testId + '\'' +
                ", testName='" + testName + '\'' +
                ", userDto=" + userDto +
                '}';
    }
}
