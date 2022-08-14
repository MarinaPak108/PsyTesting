package com.psytest.web.form;


public class AnswerCreationParameters {

    private final String answer;
    private final Integer answerValue;
    private final Long questionId;

    public AnswerCreationParameters(String answer, Integer answerValue, Long questionId) {
        this.answer = answer;
        this.answerValue = answerValue;
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getAnswerValue() {
        return answerValue;
    }

    public Long getQuestionId() {
        return questionId;
    }
}
