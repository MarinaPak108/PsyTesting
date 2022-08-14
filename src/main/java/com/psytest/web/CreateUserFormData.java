package com.psytest.web;

import com.psytest.web.form.UserCreationParameters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class CreateUserFormData {
    @NotNull
    @Size(min = 1, max = 400)
    private String login;

    @NotNull
    @Size(min = 1, max = 400)
    private String password;

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
    private Date birthday;
    @NotNull
    @Size(min = 1, max = 400)
    private String nationality;
    private String sex;


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public UserCreationParameters toParameters() {
        return new UserCreationParameters(login, password, name, surname, fathersName, birthday, nationality, sex);
    }

}
