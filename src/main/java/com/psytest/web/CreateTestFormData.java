package com.psytest.web;

import com.psytest.web.form.TestCreationParameters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTestFormData {


    @NotNull
    @Size(min = 1, max = 400)
    private String testName;

    @NotNull
    @Size(min = 1, max = 400)
    private String description;

    @NotNull
    private Integer categoryCount;


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public TestCreationParameters toParameters() {
        return new TestCreationParameters(testName, description, categoryCount);
    }

}
