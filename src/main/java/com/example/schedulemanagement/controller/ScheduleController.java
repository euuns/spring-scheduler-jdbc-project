package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {


    // Controller에서 Service에 접근하기 위한 객체
    // 생성자에서 인스턴스하여 사용
    private final ScheduleService service;

    public ScheduleController(ScheduleService service){
        this.service = service;
    }



    // 정보 입력
    @PostMapping("/add")
    public ResponseEntity<ScheduleResponseDto> addSchedule(@RequestBody ScheduleRequestDto dto){
        return new ResponseEntity<>(service.addSchedule(dto), HttpStatus.OK);
    }


    // 일정 목록 출력
    @GetMapping()
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleList() {
        return new ResponseEntity<>(service.getScheduleList(), HttpStatus.OK);
    }


    // 작성자 이름으로 리스트 출력
    @GetMapping("/user/{user}")
    public ResponseEntity<List<ScheduleResponseDto>> getUserNameList(@PathVariable String user) {
        return new ResponseEntity<>(service.getUserNameList(user), HttpStatus.OK);
    }



    // 특정 날짜 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ScheduleResponseDto>> getDate(@PathVariable LocalDate date) {
        return new ResponseEntity<>(service.getDate(date), HttpStatus.OK);
    }


    // 선택한 일정 출력
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(service.getSchedule(id), HttpStatus.OK);
    }


    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return new ResponseEntity<>(service.updateSchedule(id,dto), HttpStatus.OK);
    }



    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        service.deleteSchedule(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
