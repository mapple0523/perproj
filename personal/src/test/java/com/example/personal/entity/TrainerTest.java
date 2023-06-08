package com.example.personal.entity;

import com.example.personal.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainerTest {

    @Autowired
    TrainerRepository trainerRepository;

    @Test
    public void make() {
        var tar1=Trainer.builder()
                .trainerId("tra4")
                .trainerPw("123")
                .location("대구광역시 북구")
                .name("복무열")
                .profile("헬스 대회 1회우승, 트레이너 우수상")
                .center("북구 피트니스센터")
                .availableFrom(LocalTime.of(15,00))
                .availableTo(LocalTime.of(22,00))
                .build();

        trainerRepository.save(tar1);

        var tar2=Trainer.builder()
                .trainerId("tra2")
                .trainerPw("123")
                .location("대구광역시 동구")
                .name("안재범")
                .profile("피트니스 대회 준우승")
                .center("동구 피트니스센터")
                .availableFrom(LocalTime.of(13,00))
                .availableTo(LocalTime.of(17,00))
                .build();

        trainerRepository.save(tar2);

        var tar3=Trainer.builder()
                .trainerId("tra3")
                .trainerPw("123")
                .location("대구광역시 남구")
                .name("조남규")
                .profile("피트니스 대회 우승")
                .center("남구 피트니스센터")
                .availableFrom(LocalTime.of(19,00))
                .availableTo(LocalTime.of(22,00))
                .build();

        trainerRepository.save(tar3);

        var tar4=Trainer.builder()
                .trainerId("tra1")
                .trainerPw("123")
                .location("대구광역시 서구")
                .name("심가영")
                .profile("서구 트레이너 우수상")
                .center("서구 피트니스센터")
                .availableFrom(LocalTime.of(18,00))
                .availableTo(LocalTime.of(20,00))
                .build();

        trainerRepository.save(tar4);


    }

}