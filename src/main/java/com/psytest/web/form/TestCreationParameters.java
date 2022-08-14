package com.psytest.web.form;


public class TestCreationParameters {

    private final String testName;
    private final String description;
    private final Integer categoryCount;

       public TestCreationParameters(String testName, String description, Integer categoryCount) {
        this.testName = testName;
        this.description = description;
        this.categoryCount = categoryCount;
    }


    public String getTestName() {
        return testName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }
}
