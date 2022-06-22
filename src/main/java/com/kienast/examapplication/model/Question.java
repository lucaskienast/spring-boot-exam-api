package com.kienast.examapplication.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "QUESTION_NAME", nullable = false)
    private String questionName;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "TEST_ID")
    private Test test;

    @OneToMany(mappedBy = "question")
    private List<PossibleAnswer> possibleAnswers;

    public Question() {
    }

    public Question(String questionName, Test test) {
        this.questionName = questionName;
        this.test = test;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<PossibleAnswer> getPossibleAnswers() {return possibleAnswers;}

    public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {this.possibleAnswers = possibleAnswers;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId) && Objects.equals(questionName, question.questionName) && Objects.equals(test, question.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionName, test);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionName='" + questionName + '\'' +
                ", testId=" + test +
                '}';
    }
}
