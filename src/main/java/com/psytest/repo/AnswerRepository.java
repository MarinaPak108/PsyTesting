package com.psytest.repo;

import com.psytest.entity.AnswerEntity;
import com.psytest.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    Optional<AnswerEntity> findByIdAndAnswer(Long id, String answer);

    Optional<AnswerEntity> getAnswerById(Long id);

    Optional<AnswerEntity> getAnswerByAnswer(String answer);

    List<AnswerEntity> getAnswerEntityByQuestion(Optional <QuestionEntity> question);

    List<AnswerEntity> findAnswerEntityByQuestion (QuestionEntity question);



}
