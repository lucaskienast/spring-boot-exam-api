package com.kienast.examapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TEST")
public class Test {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TEST_ID")
    private Long testId;

    @Column(name = "TEST_NAME", nullable = false, unique = true)
    private String testName;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "USER_ID")
    private User createdBy;

    @OneToMany(mappedBy = "test", fetch = EAGER)
    @JsonManagedReference
    private List<Question> questions;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    public Test() {
    }

    public Test(String testName, User createdBy) {
        this.testName = testName;
        this.createdBy = createdBy;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Question> getQuestions() {return questions;}

    public void setQuestions(List<Question> questions) {this.questions = questions;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(testId, test.testId) && Objects.equals(testName, test.testName) && Objects.equals(createdAt, test.createdAt) && Objects.equals(createdBy, test.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, testName, createdAt, createdBy);
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", testName='" + testName + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
