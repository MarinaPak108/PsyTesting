package com.psytest.entity;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class AnswerEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "answer", nullable = false, length = 100)
    private String answer;

    @Basic
    @Column(name = "answer_value", nullable = false)
    private Integer answerValue;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
    private QuestionEntity question;

    public AnswerEntity(Long id, String answer, Integer answerValue) {
        this.id = id;
        this.answer = answer;
        this.answerValue = answerValue;
    }

    public AnswerEntity(String answer, Integer answerValue) {
        this.answer = answer;
        this.answerValue = answerValue;
    }

    public AnswerEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(Integer answerValue) {
        this.answerValue = answerValue;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
