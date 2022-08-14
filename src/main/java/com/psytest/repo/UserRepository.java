package com.psytest.repo;

import com.psytest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginAndPassword(String login, String password);

    Optional<UserEntity> getUserByLogin(String login);

    List<UserEntity> getUserByTeacherNotNullOrderById();

    List<UserEntity> getUserByTeacherNull();

    UserEntity getUserById(Long id);

}
