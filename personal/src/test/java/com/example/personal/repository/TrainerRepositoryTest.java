package com.example.personal.repository;

import com.example.personal.entity.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrainerRepositoryTest {

    @Autowired
    TrainerRepository trainerRepository;

    @Test
    public void test() {
        //var a = trainerRepository.findTop3ByLocationContaining("북구");

//        var a = trainerRepository.findByTrainerId("tra3").get();

        var a = trainerRepository.findTrainer(LocalTime.of(20,00) ,10L);

        System.out.println(a);

    }




}