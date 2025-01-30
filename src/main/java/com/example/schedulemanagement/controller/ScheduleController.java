package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final Map<Long,Schedule> scheduleList = new HashMap();


    // 정보 입력
    @PostMapping("/add")
    public ScheduleResponseDto addSchedule(@RequestBody ScheduleRequestDto dto){
        Long id = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet())+1;
        LocalDate date = new Schedule().getDate();

        Schedule schedule = new Schedule(id, dto.getTitle(), dto.getUser(), dto.getContent(), dto.getPassword(), date);
        scheduleList.put(id, schedule);

        return new ScheduleResponseDto(schedule);
    }


    // 일정 목록 출력
    @GetMapping()
    public List<ScheduleResponseDto> getScheduleList() {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return responseList;
    }


    // 작성자 이름으로 리스트 출력
    @GetMapping("/user/{user}")
    public List<ScheduleResponseDto> getUserNameList(@PathVariable String user) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getUser().equals(user)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return responseList;
    }



    // 특정 날짜 조회
    @GetMapping("/date/{date}")
    public List<ScheduleResponseDto> getDate(@PathVariable LocalDate date) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getDate().equals(date)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return responseList;
    }


    // 선택한 일정 출력
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleList.get(id);
        return new ScheduleResponseDto(schedule);
    }

}
