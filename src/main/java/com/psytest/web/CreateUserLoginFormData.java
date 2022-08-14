package com.psytest.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserLoginFormData {
    @NotNull
    @Size(min = 1, max = 400)
    private String username;

    @NotNull
    @Size(min = 1, max = 400)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
