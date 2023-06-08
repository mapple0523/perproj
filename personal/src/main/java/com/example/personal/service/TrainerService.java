package com.example.personal.service;

import com.example.personal.dto.ReviewDto;
import com.example.personal.dto.TrainerDto;
import com.example.personal.dto.UserDto;
import com.example.personal.entity.Trainer;
import com.example.personal.entity.User;
import com.example.personal.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    @Transactional
    //지역으로 트레이너 찾기
    public List<TrainerDto> trainerLocationFind(String location) {
        var trainer = trainerRepository.findTop3ByLocationContaining(location);
        List<TrainerDto> dtoTrainer = new ArrayList<>();
        if(trainer.isPresent()) {
            trainer.get().forEach(t -> {
                var find = entityToDto(t);
                dtoTrainer.add(find);
            });
        }
        return dtoTrainer;
    }

    @Transactional
    public List<TrainerDto> findTrainer(LocalTime time, Long idx) {
        List<TrainerDto> dtoReview = new ArrayList<>();
        var trainer = trainerRepository.findTrainer(time, idx);
        List<TrainerDto> dtoTrainer = new ArrayList<>();
        if(trainer.isPresent()) {
            trainer.get().forEach(t -> {
                var find = entityToDto(t);
                dtoTrainer.add(find);
            });
        }
        return dtoTrainer;
    }

    @Transactional
    public Optional trainerIdFind(Long idx) {
        var trainer = trainerRepository.findById(idx);
        return trainer;

    }


    private TrainerDto entityToDto(Trainer trainer) {
        var dto = TrainerDto.builder()
                .idx(trainer.getIdx())
                .trainerId(trainer.getTrainerId())
                .trainerPw(trainer.getTrainerPw())
                .location(trainer.getLocation())
                .name(trainer.getName())
                .profile(trainer.getProfile())
                .center(trainer.getCenter())
                .availableFrom(trainer.getAvailableFrom())
                .availableTo(trainer.getAvailableTo())
                .createdAt(trainer.getCreatedAt())
                .updatedAt(trainer.getUpdatedAt())
                .build();

        return dto;
    }

    private Trainer dtoToEntity(TrainerDto trainerDto) {
        var dto = Trainer.builder()
                .idx(trainerDto.getIdx())
                .trainerId(trainerDto.getTrainerId())
                .trainerPw(trainerDto.getTrainerPw())
                .location(trainerDto.getLocation())
                .name(trainerDto.getName())
                .profile(trainerDto.getProfile())
                .center(trainerDto.getCenter())
                .availableFrom(trainerDto.getAvailableFrom())
                .availableTo(trainerDto.getAvailableTo())
                .createdAt(trainerDto.getCreatedAt())
                .updatedAt(trainerDto.getUpdatedAt())
                .build();

        return dto;
    }




}
