package com.example.personal.service;

import com.example.personal.dto.MatchingDto;
import com.example.personal.dto.ReviewDto;
import com.example.personal.dto.UserDto;
import com.example.personal.entity.Matching;
import com.example.personal.entity.User;
import com.example.personal.repository.MatchingRepository;
import com.example.personal.repository.TrainerRepository;
import com.example.personal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    private final TrainerRepository trainerRepository;

    public MatchingDto addMatching(LocalDateTime localDateTime, Long userIdx, Long trainerIdx) {
        MatchingDto dto =
                MatchingDto.builder()
                        .time(localDateTime)
                        .userIdx(userIdx)
                        .trainerIdx(trainerIdx)
                        .build();
        var matching = matchingRepository.save(dtoToEntity(dto));
        return entityToDto(matching);
    }

    public Long findMatching(LocalDateTime localDateTime){
        var a= matchingRepository.findMatching(localDateTime);
        return a;
    }

    public Optional<Matching> findTimeAndUser(LocalDateTime time, Long userIdx) {
        var a = matchingRepository.findByTimeAndUserIdx(time,userIdx);
        return a;
    }

    public Optional<Matching> findTime(LocalDateTime time) {
        var a = matchingRepository.findByTime(time);
        return a;
    }

    public List<MatchingDto> findTrainerAndMatching(Long idx) {
        var matching = matchingRepository.findUserIdxAndTrainerName(idx);
        List<MatchingDto> dtoMatching = new ArrayList<>();
        if(matching.isPresent()) {
            matching.get().forEach(t -> {
                var find = entityToDto(t);
                dtoMatching.add(find);
            });
        }

        return dtoMatching;
    }

    @Transactional
    public void delete(Long idx) {
        matchingRepository.DeleteByIdx(idx);
    }


    private MatchingDto entityToDto(Matching matching) {

        var dto = MatchingDto.builder()
                .idx(matching.getIdx())
                .time(matching.getTime())
                .userIdx(matching.getUser().getIdx())
                .trainerIdx(matching.getMatrainer().getIdx())
                .createdAt(matching.getCreatedAt())
                .updatedAt(matching.getUpdatedAt())
                .build();
        return dto;
    }

    private Matching dtoToEntity(MatchingDto matchingDto) {
        var user = userRepository.findById(matchingDto.getUserIdx()).get();
        var trainer = trainerRepository.findById(matchingDto.getTrainerIdx()).get();

        var dto = Matching.builder()
                .idx(matchingDto.getIdx())
                .time(matchingDto.getTime())
                .user(user)
                .matrainer(trainer)
                .createdAt(matchingDto.getCreatedAt())
                .updatedAt(matchingDto.getUpdatedAt())
                .build();

        return dto;
    }


}
