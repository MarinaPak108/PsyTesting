package com.psytest.web.form;

public class TeacherCreationParameters {
    private final String name;
    private final String surname;
    private final String fathersName;
    private final String login;
    private final String password;
    private final Boolean isCurator;

    public TeacherCreationParameters(String name, String surname,
                                     String fathersName, String login,
                                     String password, Boolean isCurator) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.login = login;
        this.password = password;
        this.isCurator = isCurator;
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getCurator() {
        return isCurator;
    }
}
