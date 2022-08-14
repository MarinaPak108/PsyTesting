package com.psytest.repo;

import com.psytest.entity.CountResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountResultRepo extends JpaRepository <CountResult, Long> {

    List<CountResult> findAllByQuestionValue(Integer questionValue);
}
