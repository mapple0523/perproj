package com.example.personal.entity;

import com.example.personal.entity.listener.DateListener;
import com.example.personal.entity.listener.LibraryEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(value = {LibraryEntityListener.class})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trainer implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true)
    private String trainerId;
    private String trainerPw;
    private String location;
    private String name;
    @Column(length=1000)
    private String profile;
    private String center;
    private LocalTime availableFrom;
    private LocalTime availableTo;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    public void addReview(Review review){
        this.reviews.add(review);
    }
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "matrainer")
    @Builder.Default
    private List<Matching> Matching = new ArrayList<>();
    public void addMatching(Matching matching){this.Matching.add(matching);}

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
