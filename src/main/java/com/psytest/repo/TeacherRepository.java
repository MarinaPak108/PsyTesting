package com.psytest.repo;

import com.psytest.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByLoginAndPassword(String login, String password);

    boolean getTeacherByLogin(String login);

    Optional<TeacherEntity> getTeacherEntityByLogin(String login);

    List<TeacherEntity> getTeacherEntityByIsCuratorIsFalseOrderById();

    List<TeacherEntity> getTeacherEntityByIsCuratorIsTrueOrderById();

}
