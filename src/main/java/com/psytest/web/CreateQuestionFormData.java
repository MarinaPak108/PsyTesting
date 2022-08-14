package com.psytest.web;

import com.psytest.web.form.QuestionCreationParameters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateQuestionFormData {


    @NotNull
    private Integer questionNumber;

    @NotNull
    private Integer questionValue;

    @NotNull
    @Size(min = 1, max = 400)
    private String question;

    @NotNull
    private Long testId;


    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Integer getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(Integer questionValue) {
        this.questionValue = questionValue;
    }

    public QuestionCreationParameters toParameters() {
        return new QuestionCreationParameters(question, questionNumber, questionValue, testId);
    }

}
