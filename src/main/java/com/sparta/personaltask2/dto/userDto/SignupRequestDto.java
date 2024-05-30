package com.sparta.personaltask2.dto.userDto;

import com.sparta.personaltask2.entity.UserRoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    private String nickname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;


    private boolean isAdmin;
    private String adminToken ="";

}
