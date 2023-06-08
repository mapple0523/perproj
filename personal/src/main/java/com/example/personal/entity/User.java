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
public class User implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    private String userPw;
    private String address;
    private String nick;
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    public void addReview(Review review){
        this.reviews.add(review);
    }
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
