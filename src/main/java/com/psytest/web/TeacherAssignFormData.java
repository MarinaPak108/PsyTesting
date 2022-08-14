package com.psytest.web;

import com.psytest.web.form.TeacherAssignParameters;

public class TeacherAssignFormData {
    private Long user;
    private Long teacher;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public TeacherAssignParameters assignParameters() {
        return new TeacherAssignParameters(user, teacher);
    }
}
