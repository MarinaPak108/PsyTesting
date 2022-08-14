package com.psytest.web.form;

public class TeacherAssignParameters {
    private final Long user;
    private final Long teacher;

    public TeacherAssignParameters(Long user, Long teacher) {
        this.user = user;
        this.teacher = teacher;
    }

    public Long getUser() {
        return user;
    }

    public Long getTeacher() {
        return teacher;
    }
}
