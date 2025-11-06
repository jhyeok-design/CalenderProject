package com.example.calenderproject.dto.request;

import com.example.calenderproject.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class CreateCommentRequest {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(max = 100)
    private String leaveComment;
    @NotBlank(message = "작성자명은 필수 입력 값입니다.")
    private String writer;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    private Schedule schedule;
}
