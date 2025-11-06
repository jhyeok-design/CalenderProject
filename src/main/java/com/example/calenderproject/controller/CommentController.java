package com.example.calenderproject.controller;

import com.example.calenderproject.dto.request.CreateCommentRequest;
import com.example.calenderproject.dto.response.CreateCommentResponse;
import com.example.calenderproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // LV5 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> saveComments(
            @PathVariable Long scheduleId,
            @RequestBody @Valid CreateCommentRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request));
    }
}
