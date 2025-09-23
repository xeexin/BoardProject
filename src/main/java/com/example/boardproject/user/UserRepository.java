package com.example.boardproject.user;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * User 엔티티에 대한 데이터베이스 접근을 처리하는 Repository 인터페이스입니다.
 * Spring Data JDBC의 CrudRepository를 상속받아 기본적인 CRUD 기능을 제공받습니다.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 로그인 ID를 기준으로 사용자를 조회합니다.
     * @param loginId 조회할 사용자의 로그인 ID
     * @return Optional<User> 객체. 사용자가 존재하지 않을 경우 Optional.empty()를 반환합니다.
     */
    Optional<User> findByLoginId(String loginId);
}