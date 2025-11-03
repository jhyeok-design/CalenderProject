package com.example.calenderproject.service;


import com.example.calenderproject.dto.CreateScheduleRequest;
import com.example.calenderproject.dto.CreateScheduleResponse;
import com.example.calenderproject.entity.Schedule;
import com.example.calenderproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                request.getPassword(),
                request.getCreatedAt(),
                request.getModifiedAt()
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

}
