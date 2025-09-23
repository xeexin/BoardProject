package com.example.boardproject.user;

import com.example.boardproject.user.dto.UserProfileResponseDto;
import com.example.boardproject.user.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 프로필 관련 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 * '/api/users' 경로의 요청을 담당합니다.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 프로필 조회 API
     * [GET] /api/users/{userId}/profile
     * @param userId 조회할 사용자의 ID
     * @return 성공 시 200 OK 상태 코드와 함께 사용자 프로필 정보를 반환합니다.
     */
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@PathVariable Long userId) {
        UserProfileResponseDto userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    /**
     * 사용자 프로필 수정 API
     * [PATCH] /api/users/{userId}/profile
     * @param userId 수정할 사용자의 ID
     * @param requestDto 수정할 정보가 담긴 요청 DTO
     * @return 성공 시 200 OK 상태 코드와 함께 수정된 사용자의 ID를 반환합니다.
     */
    @PatchMapping("/{userId}/profile")
    public ResponseEntity<Long> updateUserProfile(@PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        Long updatedUserId = userService.updateUserProfile(userId, requestDto);
        return ResponseEntity.ok(updatedUserId);
    }
}