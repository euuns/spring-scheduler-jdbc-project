package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto addSchedule(Schedule schdule);
    List<ManagementResponseDto> getScheduleList();
//    List<ManagementResponseDto> getUserNameList(String name);
//    List<ManagementResponseDto> getDate(LocalDate date);
//    Schedule getSchedule(Long id);
//    void deleteSchedule(Long id);
}
