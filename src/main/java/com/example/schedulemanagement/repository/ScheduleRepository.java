package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {

    Schedule addSchedule(Schedule schdule);
    List<ScheduleResponseDto> getScheduleList();
    List<ScheduleResponseDto> getUserNameList(String name);
    List<ScheduleResponseDto> getDate(LocalDate date);
    Schedule getSchedule(Long id);
    void deleteSchedule(Long id);
}
