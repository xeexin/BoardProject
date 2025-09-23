package com.example.boardproject.user.dto;

import com.example.boardproject.user.User;
import lombok.Getter;

/**
 * 사용자 프로필 조회 시 응답으로 사용될 DTO (Data Transfer Object) 입니다.
 * 비밀번호와 같은 민감한 정보는 제외됩니다.
 */
@Getter
public class UserProfileResponseDto {

    /**
     * 사용자 로그인 ID.
     */
    private final String loginId;

    /**
     * 사용자 닉네임.
     */
    private final String nickname;

    /**
     * 사용자 이메일 주소.
     */
    private final String email;

    /**
     * User 엔티티를 DTO로 변환하는 생성자.
     * @param user User 엔티티 객체
     */
    public UserProfileResponseDto(User user) {
        this.loginId = user.getLoginId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}