package com.example.personal.repository;

import com.example.personal.entity.Review;
import com.example.personal.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {



}
