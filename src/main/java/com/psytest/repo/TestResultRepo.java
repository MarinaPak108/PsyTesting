package com.psytest.repo;

import com.psytest.entity.TestResultEntity;
import com.psytest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepo extends JpaRepository<TestResultEntity, Long> {

    List<TestResultEntity> getAllByUser (UserEntity user);
}
