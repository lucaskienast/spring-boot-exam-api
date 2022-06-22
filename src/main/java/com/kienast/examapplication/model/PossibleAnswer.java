package com.kienast.examapplication.model;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
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

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public PossibleAnswer() {
    }

    public PossibleAnswer(String answer, boolean isCorrect, Question question) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PossibleAnswer that = (PossibleAnswer) o;
        return isCorrect == that.isCorrect && Objects.equals(possibleAnswerId, that.possibleAnswerId) && Objects.equals(answer, that.answer) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleAnswerId, answer, isCorrect, question);
    }

    @Override
    public String toString() {
        return "PossibleAnswer{" +
                "possibleAnswerId=" + possibleAnswerId +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + question +
                '}';
    }
}
