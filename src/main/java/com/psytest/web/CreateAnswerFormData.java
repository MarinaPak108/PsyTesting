package com.psytest.web;

import com.psytest.web.form.AnswerCreationParameters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateAnswerFormData {


    @NotNull
    @Size(min = 1, max = 400)
    private String answer;

    @NotNull
    private Integer answerValue;

    @NotNull
    private Long questionId;


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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public AnswerCreationParameters toParameters() {
        return new AnswerCreationParameters(answer, answerValue, questionId);
    }

}
