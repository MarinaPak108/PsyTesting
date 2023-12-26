package com.psytest.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "psy_t_db")
public class UserEntity {
    private Long id;
    private String name;
    private String surname;
    private String fathersName;
    private String login;
    private String password;
    private Date birthday;
    private String nationality;
    private String sex;
    private TeacherEntity teacher;
    private List<TestResultEntity> results;

    protected UserEntity() {
    }

    public UserEntity(String name, String surname, String fathersName, String login, String password, Date birthday, String nationality, String sex) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.nationality = nationality;
        this.sex = sex;
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
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "nationality", nullable = false, length = 25)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = 15)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ManyToOne
    @JoinColumn(name = "curator_id", nullable = false, insertable = false, updatable = false)
    public TeacherEntity getTeacher() {
        return teacher;
    }


    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    public List<TestResultEntity> getResults() {
        return results;
    }

    public void setResults(List<TestResultEntity> results) {
        this.results = results;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(fathersName, user.fathersName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(birthday, user.birthday) && Objects.equals(nationality, user.nationality) && Objects.equals(sex, user.sex) && Objects.equals(teacher, user.teacher) && Objects.equals(results, user.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, fathersName, login, password, birthday, nationality, sex, teacher, results);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fathersName='" + fathersName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", nationality='" + nationality + '\'' +
                ", sex='" + sex + '\'' +
                //", teacher= prof." + teacher.getId()+
                //", results=" + results +
                '}';
    }
}