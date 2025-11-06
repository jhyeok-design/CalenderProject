package com.example.calenderproject.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter

// Lv1 일정 생성 dto
public class CreateScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public CreateScheduleResponse(Long id, String title, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
