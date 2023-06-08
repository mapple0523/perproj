package com.example.personal.repository;

import com.example.personal.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {

    @Query(value ="SELECT m FROM Matching m WHERE m.user.idx =:idx")
    Optional<List<Matching>> findUser(@Param("idx") Long idx);

    @Query(value ="SELECT m.idx FROM Matching m WHERE m.time =:time")
    Long findMatching(@Param("time") LocalDateTime time);

    Optional<Matching> findByTimeAndUserIdx(LocalDateTime time, Long userIdx);

    Optional<Matching> findByTime(LocalDateTime time);
    @Query(value="SELECT m,m.matrainer.name FROM Matching m WHERE m.user.idx=:idx")
    Optional<List<Matching>> findUserIdxAndTrainerName(@Param("idx")Long idx);

    @Modifying
    @Transactional
    @Query(value = "Delete from Matching m where m.idx =:idx ")
    void DeleteByIdx(@Param("idx") Long idx);
}
