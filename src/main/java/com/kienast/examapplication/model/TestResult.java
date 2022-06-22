package com.kienast.examapplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TEST_RESULT")
public class TestResult {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TEST_RESULT_ID")
    private Long testResultId;

    @Column(name = "RESULT")
    private Double result;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "TEST_ID")
    private Test test;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "USER_ID")
    private User createdBy;

    @Column(
            name = "CREATED_AT",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false,
            updatable = false
    )
    private Date createdAt;

    public TestResult() {
    }

    public TestResult(Double result, Test test, User createdBy, Date createdAt) {
        this.result = result;
        this.test = test;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Long testResultId) {
        this.testResultId = testResultId;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResult that = (TestResult) o;
        return Objects.equals(testResultId, that.testResultId) && Objects.equals(result, that.result) && Objects.equals(test, that.test) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testResultId, result, test, createdBy, createdAt);
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "testResultId=" + testResultId +
                ", testResult=" + result +
                ", testId=" + test +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
