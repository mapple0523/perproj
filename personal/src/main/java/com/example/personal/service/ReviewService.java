package com.example.personal.service;

import com.example.personal.dto.ReviewDto;
import com.example.personal.entity.Review;
import com.example.personal.repository.ReviewRepository;
import com.example.personal.repository.TrainerRepository;
import com.example.personal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;

    @Transactional
    public List<ReviewDto> findAllReview() {
        List<ReviewDto> dtoReview = new ArrayList<>();
        var review = reviewRepository.findAll();
        review.forEach(r->{
            dtoReview.add(entityToDto(r));
        });
        return dtoReview;
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto){
        return entityToDto(reviewRepository.save(dtoToEntity(reviewDto)));
    }



    private  ReviewDto entityToDto(Review review) {
        var dto = ReviewDto.builder()
                .idx(review.getIdx())
                .userIdx(review.getUser().getIdx())
                .trainerIdx(review.getTrainer().getIdx())
                .title(review.getTitle())
                .contents(review.getContents())
                .rating(review.getRating())
                .updatedAt(review.getUpdatedAt())
                .createdAt(review.getCreatedAt())
                .build();
        return  dto;
    }


    private Review dtoToEntity(ReviewDto reviewDto) {

        var user = userRepository.findById(reviewDto.getUserIdx()).get();
        var trainer = trainerRepository.findById(reviewDto.getTrainerIdx()).get();

        var entity = Review.builder()
                .idx(reviewDto.getIdx())
                .title(reviewDto.getTitle())
                .contents(reviewDto.getContents())
                .rating(reviewDto.getRating())
                .trainer(trainer)
                .user(user)
                .build();

        return entity;
    }


}
