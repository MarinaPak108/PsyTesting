package com.psytest.entity;

import javax.persistence.*;

@Entity
@Table(name = "count_result")
public class CountResult {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "question_value", nullable = true)
    private Integer questionValue;

    @Column(name = "min_value", nullable = true)
    private Double minValue;

    @Column(name = "max_value", nullable = true)
    private Double maxValue;

    @Column(name = "level", nullable = true, length = 25)
    private String level;

    public CountResult(Long id, Integer questionValue, Double minValue, Double maxValue, String level) {
        this.id = id;
        this.questionValue = questionValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.level = level;
    }

    public CountResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(Integer questionValue) {
        this.questionValue = questionValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
