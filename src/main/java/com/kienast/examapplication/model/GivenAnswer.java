package com.kienast.examapplication.model;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "GIVEN_ANSWER")
public class GivenAnswer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "GIVEN_ANSWER_ID")
    private Long givenAnswerId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "possibleAnswerId")
    private PossibleAnswer answer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "questionId")
    private Question questionId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    public GivenAnswer() {
    }

    public GivenAnswer(PossibleAnswer answer, Question questionId, User createdBy, Date createdAt) {
        this.answer = answer;
        this.questionId = questionId;
        this.createdBy = createdBy;
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

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
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
        GivenAnswer that = (GivenAnswer) o;
        return Objects.equals(givenAnswerId, that.givenAnswerId) && Objects.equals(answer, that.answer) && Objects.equals(questionId, that.questionId) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenAnswerId, answer, questionId, createdBy, createdAt);
    }

    @Override
    public String toString() {
        return "GivenAnswer{" +
                "givenAnswerId=" + givenAnswerId +
                ", answer=" + answer +
                ", questionId=" + questionId +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
