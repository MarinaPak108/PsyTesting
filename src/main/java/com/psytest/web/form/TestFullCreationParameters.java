package com.psytest.web.form;


import java.util.List;

public class TestFullCreationParameters {

    private final List<Long> answerId;

    public TestFullCreationParameters(List<Long> answerId) {
        this.answerId = answerId;
    }

    public List<Long> getAnswerId() {
        return answerId;
    }
}

