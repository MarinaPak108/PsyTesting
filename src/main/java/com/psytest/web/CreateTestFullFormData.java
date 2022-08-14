package com.psytest.web;

import com.psytest.web.form.TestFullCreationParameters;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateTestFullFormData {

    @NotNull
    private List<Long> answerId;




    public TestFullCreationParameters toParameters() {
        return new TestFullCreationParameters(answerId);
    }

}
