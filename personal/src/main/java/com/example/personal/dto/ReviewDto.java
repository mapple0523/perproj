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
public class ReviewDto implements DateListener {
    private String HttpStatus;

    private Long userIdx;
    private Long trainerIdx;
    private Integer rating;
    private Long idx;
    private String title;
    private String contents;//사용할때 쿼리해줌
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
