package com.psytest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher", schema = "psy_t_db")
public class TeacherEntity {
    private Long id;
    private String name;
    private String surname;
    private String fathersName;
    private String login;
    private String password;
    private Boolean isCurator;
    private List<UserEntity> users;

    public TeacherEntity(String name, String surname, String fathersName, String login, String password, Boolean isCurator) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.login = login;
        this.password = password;
        this.isCurator = isCurator;
    }

    public TeacherEntity() {

    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 35)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 35)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "fathersname", nullable = false, length = 35)
    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 35)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 35)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "is_curator", nullable = true)
    public Boolean getIsCurator() {
        return isCurator;
    }

    public void setIsCurator(Boolean isCurator) {
        this.isCurator = isCurator;
    }

    @OneToMany
    @JoinColumn(name = "curator_id")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}

