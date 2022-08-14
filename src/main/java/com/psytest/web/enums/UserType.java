package com.psytest.web.enums;

public enum UserType {
    TEACHER("teacher"),
    USER("user"),
    UNDEFINED("no user");

    private String shortName;

    UserType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
