package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.apache.el.stream.Optional;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto addSchedule(Schedule schdule);

    List<ManagementResponseDto> getScheduleList();
    List<ManagementResponseDto> getUserNameList(String name);
    List<ManagementResponseDto> getDateList(LocalDate date);
    List<ManagementResponseDto> getSchedule(Long id);

    List<ScheduleResponseDto> findUserId(Long id);

    int updateSchedule(Long id, String title, String contnet, LocalDate date);

    int deleteSchedule(Long id);
}
