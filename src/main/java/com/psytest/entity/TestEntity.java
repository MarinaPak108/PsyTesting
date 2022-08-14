package com.psytest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests", schema = "psytesting")
public class TestEntity {
    private Long id;
    private String testName;
    private String description;
    private Integer categoryCount;
    private List<QuestionEntity> questionsList;

    private List<TestResultEntity> results;

    public TestEntity(Long id, String testName, String description, List<QuestionEntity> questionsList) {
        this.id = id;
        this.testName = testName;
        this.description = description;
        this.questionsList = questionsList;
    }

    public TestEntity(Long id, String testName, String description) {
        this.id = id;
        this.testName = testName;
        this.description = description;
    }

    public TestEntity() {
    }

    public TestEntity(String testName, String description, Integer categoryCount) {
        this.testName = testName;
        this.description = description;
        this.categoryCount = categoryCount;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "test_name", nullable = false, length = 50)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "category_count", nullable = true)
    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    @OneToMany
    @JoinColumn(name = "test_id")
    public List<QuestionEntity> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionEntity> questionsList) {
        this.questionsList = questionsList;
    }

    @OneToMany
    @JoinColumn(name = "test_id")
    public List<TestResultEntity> getResults() {
        return results;
    }

    public void setResults(List<TestResultEntity> results) {
        this.results = results;
    }

}
