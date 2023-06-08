package com.example.personal.service;

import com.example.personal.dto.UserDto;
import com.example.personal.entity.User;
import com.example.personal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    //로그인
    public UserDto login(UserDto userDto) {
        var user = userRepository.
                findByUserIdAndUserPw(userDto.getUserId(), userDto.getUserPw());
        if(user.isPresent()) {
            return entityToDto(userRepository.
                    findByUserIdAndUserPw(userDto.getUserId(), userDto.getUserPw()).get());
        }
        return null;
    }

    //회원가입
    public UserDto signIn(UserDto dto) {
        var user = userRepository.save(dtoToEntity(dto));
        return entityToDto(user);
    }

//    public Optional<User> findUser(Long idx){
//        var user = userRepository.findById(idx);
//        return user;
//    }

    private UserDto entityToDto(User user) {
        var dto = UserDto.builder()
                .idx(user.getIdx())
                .nick(user.getNick())
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

        return dto;
    }

    private User dtoToEntity(UserDto userDto) {

        var dto = User.builder()
                .userId(userDto.getUserId())
                .userPw(userDto.getUserPw())
                .address(userDto.getAddress())
                .nick(userDto.getNick())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();

        return dto;
    }

}
