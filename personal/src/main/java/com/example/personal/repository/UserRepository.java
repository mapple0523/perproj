package com.example.personal.repository;

import com.example.personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndUserPw(String userId, String userPw);

    Optional<User> findByUserId(String userId);



}
