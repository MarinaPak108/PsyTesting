package com.psytest.repo;

import com.psytest.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    Optional<TestEntity> findByIdAndTestName(Long id, String testName);

    TestEntity getTestById(Long id);

    Optional<TestEntity> getTestByTestName(String testName);

}
