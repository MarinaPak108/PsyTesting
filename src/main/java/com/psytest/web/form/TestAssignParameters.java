package com.psytest.web.form;

public class TestAssignParameters {
    private final Long test;
    private final Long question;


    public TestAssignParameters(Long test, Long question) {
        this.test = test;
        this.question = question;
    }

    public Long getTest() {
        return test;
    }

    public Long getQuestion() {
        return question;
    }
}
