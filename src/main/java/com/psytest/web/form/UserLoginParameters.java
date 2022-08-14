package com.psytest.web.form;

public class UserLoginParameters {
    private final String login;
    private final String password;

    public UserLoginParameters(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}


