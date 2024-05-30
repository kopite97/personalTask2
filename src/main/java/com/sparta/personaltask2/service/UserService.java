package com.sparta.personaltask2.service;

import com.sparta.personaltask2.dto.userDto.SignupRequestDto;
import com.sparta.personaltask2.entity.User;
import com.sparta.personaltask2.entity.UserRoleEnum;
import com.sparta.personaltask2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final String ADMINE_TOKEN = "ADMINE_TOKENNNNNN";
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional< User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if(requestDto.isAdmin()){
            if(!ADMINE_TOKEN.equals(requestDto.getAdminToken())){
                throw new IllegalArgumentException("관리자 암호가 일치하지 않습니다");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(nickname,username,password,role);
        userRepository.save(user);
    }
}
