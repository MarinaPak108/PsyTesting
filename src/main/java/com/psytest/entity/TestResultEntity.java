package com.psytest.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "test_result")
public class TestResultEntity {
    private Long id;
    private UserEntity user;
    private TestEntity test;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String results;


    public TestResultEntity(Long id, Timestamp dateStart, Timestamp dateEnd, String results) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.results = results;
    }

    public TestResultEntity(UserEntity user) {
        this.user = user;
    }

    public TestResultEntity() {
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

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name = "test_id", nullable = true, insertable = false, updatable = false)
    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    @Column(name = "date_start", nullable = true)
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "date_end", nullable = true)
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Column(name = "results", nullable = true)
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}

