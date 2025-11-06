package com.example.calenderproject.service;


import com.example.calenderproject.dto.request.CreateScheduleRequest;
import com.example.calenderproject.dto.request.DeleteScheduleRequest;
import com.example.calenderproject.dto.request.UpdateScheduleRequest;
import com.example.calenderproject.dto.response.*;
import com.example.calenderproject.entity.Comment;
import com.example.calenderproject.entity.Schedule;
import com.example.calenderproject.repository.CommentRepository;
import com.example.calenderproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    public final ScheduleRepository scheduleRepository;
    public final CommentRepository commentRepository;


    // Lv1 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()

        );
        Schedule scheduleSave = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                scheduleSave.getId(),
                scheduleSave.getTitle(),
                scheduleSave.getContent(),
                scheduleSave.getName(),
                scheduleSave.getCreatedAt(),
                scheduleSave.getModifiedAt()

        );
    }

    // Lv2 일정 전체 확인
    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAll(String name) {
        List<Schedule> schedules = scheduleRepository.findAll();

        if (name != null && !name.isEmpty()) {
            schedules = schedules.stream()
                    .filter(schedule -> schedule.getName().equals(name))
                    .collect(Collectors.toList());
        }

        schedules.sort(Comparator.comparing(Schedule::getModifiedAt).reversed());

        List<GetAllScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetAllScheduleResponse dto = new GetAllScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // Lv2, 6 단일 일정 확인(댓글 확인도 추가)
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = exceptionHandler(scheduleId);

        List<Comment> comments = commentRepository.findBySchedule(schedule);
        List<GetCommentsResponse> leaveContentList = comments.stream()
                .map(comment -> new GetCommentsResponse(
                        comment.getId(),
                        comment.getLeaveComment(),
                        comment.getWriter(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()))
                .collect(Collectors.toList());


        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                leaveContentList


        );
    }

    // Lv3 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = exceptionHandler(scheduleId);

        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.updateSchedule(
                    request.getTitle(),
                    request.getName()
            );
        } else {
            throw new IllegalArgumentException("비빌번호가 잘못됐습니다.");
        }

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // Lv4 일정 삭제
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = exceptionHandler(scheduleId);


        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비빌번호가 틀렸습니다.");
        }

        scheduleRepository.deleteById(scheduleId);
    }


    // 예외 처리 메서드
    private Schedule exceptionHandler(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );
    }
}
