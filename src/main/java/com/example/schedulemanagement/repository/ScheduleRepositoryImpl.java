package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final Map<Long, Schedule> scheduleList = new HashMap();


    @Override
    public Schedule addSchedule(Schedule schdule) {
        Long id = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        LocalDate date = LocalDate.now();

        schdule.setId(id);
        schdule.setDate(date);
        scheduleList.put(id, schdule);

        return schdule;
    }

    @Override
    public List<ScheduleResponseDto> getScheduleList() {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()) {
            responseList.add(new ScheduleResponseDto(schedule));
        }

        return responseList;
    }



    @Override
    public List<ScheduleResponseDto> getUserNameList(String name) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getUser().equals(name)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return responseList;
    }

    @Override
    public List<ScheduleResponseDto> getDate(LocalDate date) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getDate().equals(date)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return responseList;
    }


    @Override
    public Schedule getSchedule(Long id) {
        return scheduleList.get(id);
    }

    @Override
    public void deleteSchedule(Long id){
        scheduleList.remove(id);
    }
}
