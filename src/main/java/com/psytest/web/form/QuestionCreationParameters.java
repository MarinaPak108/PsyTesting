package com.psytest.web.form;


public class QuestionCreationParameters {

    private final String question;
    private final Integer questionNumber;

    private final Integer questionValue;
    private final Long testId;

    public QuestionCreationParameters(String question, Integer questionNumber, Integer questionValue, Long testId) {
        this.question = question;
        this.questionNumber = questionNumber;
        this.questionValue = questionValue;
        this.testId = testId;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public Long getTestId() {
        return testId;
    }

    public Integer getQuestionValue() {
        return questionValue;
    }
}
