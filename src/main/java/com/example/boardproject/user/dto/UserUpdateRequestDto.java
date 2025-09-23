package com.example.boardproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 프로필 수정 시 요청으로 사용될 DTO (Data Transfer Object) 입니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    /**
     * 새로 변경할 닉네임.
     */
    private String nickname;

    /**
     * 새로 변경할 이메일 주소.
     */
    private String email;
}