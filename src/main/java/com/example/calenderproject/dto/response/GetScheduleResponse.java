package com.example.calenderproject.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

import java.util.List;

@Getter

// Lv6 단일 일정 + 댓글 확인  dto
public class GetScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentsResponse> leaveContentList;

    public GetScheduleResponse(Long id, String title, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentsResponse> leaveContentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.leaveContentList = leaveContentList;
    }
}

