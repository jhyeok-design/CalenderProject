package com.example.calenderproject.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter

// Lv3 일정 수정 dto
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdateScheduleResponse(Long id, String title, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
