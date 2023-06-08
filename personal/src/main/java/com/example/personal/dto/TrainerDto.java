package com.example.personal.dto;


import com.example.personal.entity.Matching;
import com.example.personal.entity.Review;
import com.example.personal.entity.listener.DateListener;
import com.example.personal.entity.listener.LibraryEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@EntityListeners(value = {LibraryEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrainerDto implements DateListener {


    private Long idx;
    private String trainerId;
    private String trainerPw;
    private String location;
    private String name;
    private String profile;
    private String center;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
