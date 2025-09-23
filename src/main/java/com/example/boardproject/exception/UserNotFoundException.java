package com.example.boardproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 요청한 사용자를 찾을 수 없을 때 발생하는 예외 클래스입니다.
 * 이 예외가 발생하면 HTTP 404 Not Found 상태 코드를 응답합니다.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}