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
import org.hibernate.annotations.Comments;
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
    private final CommentService commentService;

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

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );

        List<Comment> comments = commentRepository.findBySchedule(schedule);
        List<GetCommentsResponse> leaveContentList = comments.stream()
                .map(comment -> new GetCommentsResponse(
                        comment.getId(),
                        comment.getLeaveComment(),
                        comment.getWriter(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()))
                .toList();


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

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );

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

    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );

        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비빌번호가 틀렸습니다.");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
