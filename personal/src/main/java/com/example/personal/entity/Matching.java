package com.example.personal.entity;


import com.example.personal.entity.listener.DateListener;
import com.example.personal.entity.listener.LibraryEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = {LibraryEntityListener.class})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Matching implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private LocalDateTime time;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY) //사용할때 쿼리해줌
    @JoinColumn(name = "user_idx")
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_idx")
    private Trainer matrainer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
