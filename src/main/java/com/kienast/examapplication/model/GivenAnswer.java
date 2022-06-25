package com.kienast.examapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "GIVEN_ANSWER")
public class GivenAnswer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "GIVEN_ANSWER_ID")
    private Long givenAnswerId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "POSSIBLE_ANSWER_ID")
    @JsonBackReference
    private PossibleAnswer answer;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "TEST_RESULT_ID")
    @JsonBackReference
    private TestResult testResult;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    public GivenAnswer() {
    }

    public GivenAnswer(PossibleAnswer answer, TestResult testResult, Date createdAt) {
        this.answer = answer;
        this.testResult = testResult;
        this.createdAt = createdAt;
    }

    public Long getGivenAnswerId() {
        return givenAnswerId;
    }

    public void setGivenAnswerId(Long givenAnswerId) {
        this.givenAnswerId = givenAnswerId;
    }

    public PossibleAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(PossibleAnswer answer) {
        this.answer = answer;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
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
        GivenAnswer that = (GivenAnswer) o;
        return Objects.equals(givenAnswerId, that.givenAnswerId) && Objects.equals(answer, that.answer) && Objects.equals(testResult, that.testResult) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenAnswerId, answer, testResult, createdAt);
    }

    @Override
    public String toString() {
        return "GivenAnswer{" +
                "givenAnswerId=" + givenAnswerId +
                ", answer=" + answer +
                ", createdAt=" + createdAt +
                '}';
    }
}
