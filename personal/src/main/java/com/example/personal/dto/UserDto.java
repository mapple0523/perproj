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
public class UserDto implements DateListener  {
    private String httpStatus;
    private Long idx;
    private String userId;
    private String userPw;
    private String nick;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
