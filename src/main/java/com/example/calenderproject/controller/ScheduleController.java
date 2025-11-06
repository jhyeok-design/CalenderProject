package com.example.calenderproject.controller;

import com.example.calenderproject.dto.request.CreateScheduleRequest;
import com.example.calenderproject.dto.request.DeleteScheduleRequest;
import com.example.calenderproject.dto.request.UpdateScheduleRequest;
import com.example.calenderproject.dto.response.CreateScheduleResponse;
import com.example.calenderproject.dto.response.GetAllScheduleResponse;
import com.example.calenderproject.dto.response.GetScheduleResponse;
import com.example.calenderproject.dto.response.UpdateScheduleResponse;
import com.example.calenderproject.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // Lv1 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody @Valid CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // Lv2 일정 보기
    @GetMapping("/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(name));
    }
    // Lv6 단일 일정보기(달린 댓글 같이 확인)
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
    // Lv3 일정 수정하기
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // Lv4 일정 삭제하기
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid DeleteScheduleRequest request
    ) {
        scheduleService.delete(scheduleId,request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

