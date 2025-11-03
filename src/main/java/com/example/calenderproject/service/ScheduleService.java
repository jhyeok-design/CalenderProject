package com.example.calenderproject.service;


import com.example.calenderproject.dto.CreateScheduleRequest;
import com.example.calenderproject.dto.CreateScheduleResponse;
import com.example.calenderproject.dto.GetScheduleResponse;
import com.example.calenderproject.entity.Schedule;
import com.example.calenderproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    public final ScheduleRepository scheduleRepository;

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
                scheduleSave.getName()

        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String name) {
        List<Schedule> schedules = scheduleRepository.findAll();

        if (name != null && !name.isEmpty()) {
            schedules = schedules.stream()
                    .filter(schedule -> schedule.getName().equals(name))
                    .toList();
        }

        schedules.sort(Comparator.comparing(Schedule::getModifiedAt).reversed());

        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName()

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
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName()
        );
    }
}
