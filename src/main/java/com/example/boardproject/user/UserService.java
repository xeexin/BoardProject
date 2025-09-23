package com.example.boardproject.user;

import com.example.boardproject.exception.UserNotFoundException;
import com.example.boardproject.user.dto.UserProfileResponseDto;
import com.example.boardproject.user.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해줍니다.
public class UserService {

    /**
     * UserRepository 의존성 주입.
     */
    private final UserRepository userRepository;

    /**
     * 특정 사용자의 프로필 정보를 조회합니다.
     * @param userId 조회할 사용자의 ID
     * @return 조회된 사용자의 프로필 정보 DTO
     * @throws UserNotFoundException 해당 ID의 사용자를 찾을 수 없을 때 발생
     */
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션으로 성능 향상
    public UserProfileResponseDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다. id: " + userId));
        return new UserProfileResponseDto(user);
    }

    /**
     * 특정 사용자의 프로필 정보를 수정합니다.
     * @param userId 수정할 사용자의 ID
     * @param requestDto 수정할 프로필 정보가 담긴 DTO
     * @return 수정된 사용자의 ID
     * @throws UserNotFoundException 해당 ID의 사용자를 찾을 수 없을 때 발생
     */
    @Transactional // 데이터 변경이 있으므로 트랜잭션 적용
    public Long updateUserProfile(Long userId, UserUpdateRequestDto requestDto) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다. id: " + userId));

        // 2. 사용자 정보 업데이트 (도메인 모델에 비즈니스 로직 위임)
        user.updateProfile(requestDto.getNickname(), requestDto.getEmail());

        // 3. 변경된 사용자 정보 저장 (Spring Data JDBC에서는 명시적 저장이 필요)
        userRepository.save(user);

        return user.getId();
    }
}