package com.example.calenderproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String content;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;}
