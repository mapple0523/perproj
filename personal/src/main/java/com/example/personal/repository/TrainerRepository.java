package com.example.personal.repository;


import com.example.personal.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<List<Trainer>> findTop3ByLocationContaining(String location);

    Optional<Trainer> findByTrainerId(String trainerId);

    List<Trainer> findByIdx(Long idx);

    @Query(value = "SELECT t from Trainer t where :time BETWEEN t.availableFrom AND t.availableTo AND t.idx =:idx ")
    Optional<List<Trainer>> findTrainer(@Param("time") LocalTime time, @Param("idx") Long idx);


}
