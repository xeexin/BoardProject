# 📖 BoardProject

## 📝 프로젝트 소개

Spring Boot, Spring Data JDBC, Spring MVC, Thymeleaf를 활용하여 개발한 간단한 웹 게시판 애플리케이션입니다.

본 프로젝트는 기본적인 CRUD 기능을 포함하며, 웹 애플리케이션 개발의 핵심적인 요소들을 학습하고 적용하는 것을 목표로 합니다.

## ✨ 주요 기능

-   **게시판**
    -   게시물 생성, 조회, 수정, 삭제 (CRUD)
    -   페이징 처리된 게시물 목록 조회
    -   게시물 작성 및 수정 시 서버 측 유효성 검사
    -   게시물 수정 및 삭제 시 비밀번호를 통한 권한 확인
-   **사용자 API**
    -   사용자 프로필 조회 API
    -   사용자 프로필 수정 API

## 🛠️ 기술 스택

### Backend
-   Java 17
-   Spring Boot 3.2.5
-   Spring Data JDBC
-   Spring MVC
-   Spring Boot Validation
-   Lombok
-   MySQL

### Frontend
-   Thymeleaf
-   Bootstrap 5

### Build Tool
-   Gradle

## 📋 API Endpoints

| Method | URI                               | 설명                 |
| :----- | :-------------------------------- | :------------------- |
| `GET`  | `/api/users/{userId}/profile`     | 사용자 프로필 정보 조회    |
| `PATCH`| `/api/users/{userId}/profile`     | 사용자 프로필 정보 수정    |

## 🗄️ 데이터베이스 스키마

이 프로젝트는 `board`와 `users` 두 개의 주요 테이블을 사용합니다.

**`board` table**
```sql
CREATE TABLE board (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(20) NOT NULL,
    content VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);
```

**`users` table**
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    email VARCHAR(255)
);
```

## 🚀 실행 방법

1.  **프로젝트 클론**
    ```bash
    git clone <repository-url>
    cd BoardProject
    ```

2.  **데이터베이스 설정**
    `src/main/resources/application.yml` 파일을 열어 자신의 데이터베이스 환경에 맞게 `datasource` 정보를 수정합니다.
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/exampledb # DB URL
        username: your_username # DB 사용자 이름
        password: your_password # DB 비밀번호
    ```

3.  **데이터베이스 및 테이블 생성**
    설정한 `datasource`에 해당하는 데이터베이스(`exampledb`)를 생성하고, 위의 데이터베이스 스키마를 참고하여 `board`와 `users` 테이블을 생성합니다.

4.  **애플리케이션 실행**
    -   **IDE 사용:** IntelliJ 등에서 `BoardProjectApplication.java` 파일을 직접 실행합니다.
    -   **Gradle 사용:** 터미널에서 아래 명령어를 실행합니다.
        ```bash
        ./gradlew bootRun
        ```

5.  **접속**
    웹 브라우저에서 `http://localhost:8080/view`로 접속하여 게시판 목록을 확인할 수 있습니다.
