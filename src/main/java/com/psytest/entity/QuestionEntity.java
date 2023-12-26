package com.psytest.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "questions", schema ="psytesting")
public class QuestionEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "question", nullable = false, length = 100)
    private String question;

    @Basic
    @Column(name = "question_number", nullable = false)
    private Integer questionNumber;

    @Basic
    @Column(name = "question_value", nullable = false)
    private Integer questionValue;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false, insertable = false, updatable = false)
    private TestEntity test;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<AnswerEntity> answerList;

    @Basic
    @NotNull
    @Column(name = "chose", nullable = false)
    private Integer choose;

    @Basic
    @NotNull
    @Column(name = "is_show", nullable = false)
    private Integer isShow;

    public QuestionEntity(Long id, String question, Integer questionNumber, Integer questionValue) {
        this.id = id;
        this.question = question;
        this.questionNumber = questionNumber;
        this.questionValue = questionValue;
    }

    public QuestionEntity(Long id, String question, Integer questionNumber, Integer questionValue, TestEntity test, List<AnswerEntity> answerList, Integer choose, Integer isShow) {
        this.id = id;
        this.question = question;
        this.questionNumber = questionNumber;
        this.questionValue = questionValue;
        this.test = test;
        this.answerList = answerList;
        this.choose = choose;
        this.isShow = isShow;
    }

    public QuestionEntity() {
    }

    public QuestionEntity(String question, Integer questionNumber, Integer questionValue) {
        this.question = question;
        this.questionNumber = questionNumber;
        this.questionValue = questionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(Integer questionValue) {
        this.questionValue = questionValue;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public List<AnswerEntity> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerEntity> answerList) {
        this.answerList = answerList;
    }

    public Integer getChoose() {
        return choose;
    }

    public void setChoose(Integer choose) {
        this.choose = choose;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
