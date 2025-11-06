package com.example.calenderproject.dto.request;

import lombok.Getter;

@Getter

// Lv3 일정 수정 dto
public class UpdateScheduleRequest {
    private String password;
    private String title;
    private String name;

}
