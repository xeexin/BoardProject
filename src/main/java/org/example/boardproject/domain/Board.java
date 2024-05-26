package org.example.boardproject.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    @Id
    private Long id;

    @NotNull(message = "이름을 입력해 주세요.")
    @NotBlank
    private String name;


    @NotNull(message = "제목을 입력해 주세요.")
    @Size(max = 20, message = "제목은 최대 20자입니다.")
    private String title;

    @NotNull
    @Size(min = 5, message = "5글자 이상 입력해 주세요.")
    private String content;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
