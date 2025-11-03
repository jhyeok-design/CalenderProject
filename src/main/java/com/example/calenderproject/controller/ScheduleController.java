package com.example.calenderproject.controller;


import com.example.calenderproject.dto.CreateScheduleRequest;
import com.example.calenderproject.dto.CreateScheduleResponse;
import com.example.calenderproject.dto.GetScheduleResponse;
import com.example.calenderproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(name));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

}

