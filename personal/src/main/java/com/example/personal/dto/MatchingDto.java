package com.example.personal.dto;

import com.example.personal.entity.listener.DateListener;
import com.example.personal.entity.listener.LibraryEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@EntityListeners(value = {LibraryEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MatchingDto implements DateListener {

    private Long idx;

    private LocalDateTime time;

    private Long userIdx;
    private Long trainerIdx;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
