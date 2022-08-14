package com.psytest.web.form;

import java.sql.Date;

public class UserCreationParameters {
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final String fathersName;
    private final Date birthday;
    private final String nationality;
    private final String sex;

    public UserCreationParameters(String login, String password,
                                  String name, String surname,
                                  String fathersName, Date birthday,
                                  String nationality, String sex) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.birthday = birthday;
        this.nationality = nationality;
        this.sex = sex;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFathersName() {
        return fathersName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public String getSex() {
        return sex;
    }

}
