package com.example.personal.entity;

import com.example.personal.entity.listener.DateListener;
import com.example.personal.entity.listener.LibraryEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {LibraryEntityListener.class})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    @Column(length = 1000)
    private String contents;
    @ColumnDefault("0")
    private Integer rating;
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_idx")
    private Trainer trainer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
