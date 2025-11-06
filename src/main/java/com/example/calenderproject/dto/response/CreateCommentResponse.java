package com.example.calenderproject.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter

// Lv5 일정에 댓글 작성 dto
public class CreateCommentResponse {

    private final Long id;
    private final String leaveComment;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long id, String leaveComment, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.leaveComment = leaveComment;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
