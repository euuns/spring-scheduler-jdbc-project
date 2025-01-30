package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto addSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> getScheduleList();
    List<ScheduleResponseDto> getUserNameList(String name);
    List<ScheduleResponseDto> getDate(LocalDate date);
    ScheduleResponseDto getSchedule(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);
    void deleteSchedule(Long id, String password);
}
