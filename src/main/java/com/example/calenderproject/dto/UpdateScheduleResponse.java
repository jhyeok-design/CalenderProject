package com.example.calenderproject.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String name;

    public UpdateScheduleResponse(Long id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }
}
