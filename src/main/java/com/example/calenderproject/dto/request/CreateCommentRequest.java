package com.example.calenderproject.dto.request;

import com.example.calenderproject.entity.Schedule;
import lombok.Getter;

@Getter

public class CreateCommentRequest {

    private String leaveComment;
    private String writer;
    private String password;
    private Schedule schedule;
}
