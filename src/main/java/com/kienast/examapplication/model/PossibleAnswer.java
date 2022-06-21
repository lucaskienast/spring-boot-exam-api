package com.kienast.examapplication.model;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "POSSIBLE_ANSWER")
public class PossibleAnswer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "POSSIBLE_ANSWER_ID")
    private Long possibleAnswerId;

    @Column(name = "ANSWER", nullable = false)
    private String answer;

    @Column(name = "IS_CORRECT", nullable = false)
    private boolean isCorrect;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "questionId")
    private Test questionId;

    public PossibleAnswer() {
    }

    public PossibleAnswer(String answer, boolean isCorrect, Test questionId) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public Long getPossibleAnswerId() {
        return possibleAnswerId;
    }

    public void setPossibleAnswerId(Long possibleAnswerId) {
        this.possibleAnswerId = possibleAnswerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Test getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Test questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PossibleAnswer that = (PossibleAnswer) o;
        return isCorrect == that.isCorrect && Objects.equals(possibleAnswerId, that.possibleAnswerId) && Objects.equals(answer, that.answer) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleAnswerId, answer, isCorrect, questionId);
    }

    @Override
    public String toString() {
        return "PossibleAnswer{" +
                "possibleAnswerId=" + possibleAnswerId +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                '}';
    }
}
