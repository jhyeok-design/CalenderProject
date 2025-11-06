package com.example.calenderproject.service;

import com.example.calenderproject.dto.request.CreateCommentRequest;
import com.example.calenderproject.dto.response.CreateCommentResponse;
import com.example.calenderproject.entity.Comment;
import com.example.calenderproject.entity.Schedule;
import com.example.calenderproject.repository.CommentRepository;
import com.example.calenderproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    // Lv5 일정 댓글 작성
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
         Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                 () -> new IllegalArgumentException("잘못된 접근입니다.")
         );

         long commentCount = commentRepository.countBySchedule(schedule);
         if (commentCount >= 10){
             throw new IllegalArgumentException("댓글을 10개이상 입력할 수 없습니다.");
         }

        Comment comment = new Comment(
                request.getLeaveComment(),
                request.getWriter(),
                request.getPassword(),
                schedule
        );
        Comment saveComments = commentRepository.save(comment);

        return new CreateCommentResponse(
                saveComments.getId(),
                saveComments.getLeaveComment(),
                saveComments.getWriter(),
                saveComments.getCreatedAt(),
                saveComments.getModifiedAt()
        );
    }
}
