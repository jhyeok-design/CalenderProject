package com.example.calenderproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

// Lv1 일정 생성 dto
public class CreateScheduleRequest {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(max = 30)
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(max = 200)
    private String content;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    private String password;
}
