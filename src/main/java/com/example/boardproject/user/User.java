package com.example.boardproject.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 사용자 정보를 담는 도메인 모델 (Entity) 클래스입니다.
 * 데이터베이스의 'users' 테이블과 매핑됩니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 기본 생성자 직접 호출 방지
@Table("users") // 데이터베이스 테이블 이름 명시
public class User {

    /**
     * 사용자의 고유 식별자 (Primary Key).
     * 데이터베이스에서 자동으로 생성됩니다.
     */
    @Id
    private Long id;

    /**
     * 사용자 로그인 ID.
     */
    private String loginId;

    /**
     * 사용자 비밀번호 (해싱된 값 저장).
     */
    private String password;

    /**
     * 사용자 닉네임.
     */
    private String nickname;

    /**
     * 사용자 이메일 주소.
     */
    private String email;

    /**
     * 빌더 패턴을 사용한 생성자.
     * 객체 생성 시 가독성과 안정성을 높입니다.
     * @param loginId 로그인 ID
     * @param password 비밀번호
     * @param nickname 닉네임
     * @param email 이메일
     */
    @Builder
    public User(String loginId, String password, String nickname, String email) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    /**
     * 사용자 프로필 정보(닉네임, 이메일)를 수정하는 메서드.
     * @param nickname 새로운 닉네임
     * @param email 새로운 이메일
     */
    public void updateProfile(String nickname, String email) {
        if (nickname != null && !nickname.isBlank()) {
            this.nickname = nickname;
        }
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
    }
}