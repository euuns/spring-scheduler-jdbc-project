package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ManagementRequestDto;
import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ManagementResponseDto addSchedule(ManagementRequestDto requestDto, ManagementResponseDto responseDto);

    List<ScheduleResponseDto> getScheduleList();

//    List<ManagementResponseDto> getUserNameList(String name);
//    List<ManagementResponseDto> getDate(LocalDate date);
//    ManagementResponseDto getSchedule(Long id);
//
//    ManagementResponseDto updateSchedule(Long id, ManagementRequestDto dto);
//    void deleteSchedule(Long id, String password);
}
