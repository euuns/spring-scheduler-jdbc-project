package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ScheduleResponseDto> addSchedule(@RequestBody ScheduleRequestDto dto){
        Long id = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet())+1;
        LocalDate date = new Schedule().getDate();

        Schedule schedule = new Schedule(id, dto.getTitle(), dto.getUser(), dto.getContent(), dto.getPassword(), date);
        scheduleList.put(id, schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }


    // 일정 목록 출력
    @GetMapping()
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleList() {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }


    // 작성자 이름으로 리스트 출력
    @GetMapping("/user/{user}")
    public ResponseEntity<List<ScheduleResponseDto>> getUserNameList(@PathVariable String user) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getUser().equals(user)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }



    // 특정 날짜 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ScheduleResponseDto>> getDate(@PathVariable LocalDate date) {
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule:scheduleList.values()) {
            if (schedule.getDate().equals(date)){
                responseList.add(new ScheduleResponseDto(schedule));
            }
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }


    // 선택한 일정 출력
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleList.get(id);
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }


    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = scheduleList.get(id);

        if(schedule.isEqualTo(requestDto)){
            schedule.update(requestDto);
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }



    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = scheduleList.get(id);
        if(schedule.isEqualTo(requestDto)){
            scheduleList.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
