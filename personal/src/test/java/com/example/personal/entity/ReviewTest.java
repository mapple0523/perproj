package com.example.personal.entity;

import com.example.personal.repository.ReviewRepository;
import com.example.personal.repository.TrainerRepository;
import com.example.personal.repository.UserRepository;
import org.hibernate.annotations.ColumnDefault;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Rollback(value = false)
    @Transactional
    @Test
    void ReviewMake() {
        var user = userRepository.findByUserId("id").get();
        var trainer = trainerRepository.findByTrainerId("tra3").get();

        var a= Review.builder()
                .title("너무너무 좋은 강사님")
                .contents("어디가 불편한지 잘알려주고 어떻게 운동해야하는지 잘 알려주심")
                .rating(5)
                .trainer(trainer)
                .user(user)
                .build();

        reviewRepository.save(a);

    }



}