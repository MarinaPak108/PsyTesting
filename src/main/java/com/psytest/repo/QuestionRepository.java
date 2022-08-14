package com.psytest.repo;

import com.psytest.entity.QuestionEntity;
import com.psytest.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findByIdAndQuestion(Long id, String question);

    Optional<QuestionEntity> getQuestionById(Long id);

    Optional<QuestionEntity> getQuestionByQuestion(String question);

    List<QuestionEntity> getQuestionEntityByTest(Optional<TestEntity> test);

    QuestionEntity getQuestionEntityByTestAndId (Optional <TestEntity> test, Long Id);

    int countQuestionEntitiesByTestIdAndQuestionValue (Long Id, Integer questionValue);


}
