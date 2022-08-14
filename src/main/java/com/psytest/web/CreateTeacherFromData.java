package com.psytest.web;

import com.psytest.web.form.TeacherCreationParameters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTeacherFromData {
    @NotNull
    @Size(min = 1, max = 400)
    private String name;
    @NotNull
    @Size(min = 1, max = 400)
    private String surname;
    @NotNull
    @Size(min = 1, max = 400)
    private String fathersName;
    @NotNull
    @Size(min = 1, max = 400)
    private String login;
    @NotNull
    @Size(min = 1, max = 400)
    private String password;
    @NotNull
    private Boolean isCurator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCurator() {
        return isCurator;
    }

    public void setCurator(Boolean curator) {
        isCurator = curator;
    }

    public TeacherCreationParameters toTeacherParamaters() {
        return new TeacherCreationParameters(name, surname, fathersName, login, password, isCurator);
    }
}
