package com.example.personal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrainerServiceTest {

    @Autowired
    TrainerService trainerService;

    @Test
    void find(){
       var a =trainerService.trainerLocationFind("북구");
       System.out.println(a);
    }

}