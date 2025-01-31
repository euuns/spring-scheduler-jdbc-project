package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ManagementRequestDto;
import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.service.ScheduleService;
import com.example.schedulemanagement.service.UsersService;
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
    private final ScheduleService scheduleService;
    private final UsersService usersService;

    public ScheduleController(ScheduleService scheduleService, UsersService usersService){
        this.scheduleService = scheduleService;
        this.usersService = usersService;
    }



    // 일정 생성
    // 요청 입력은 Schedule-title,content / Users-id,password
    @PostMapping("/add")
    public ResponseEntity<ManagementResponseDto> addSchedule(@RequestBody ManagementRequestDto requestDto){

        // 입력한 id로 User정보를 가져와 응답DTO로 반환
        // 아직까지 응답DTO에는 userId, name, password만 저장
        ManagementResponseDto responseDto = usersService.getUsers(requestDto.getUserId());

        // 응답DTO에 나머지 Schedule을 담아서 반환하기 위해 Service를 호출해 로직 수행
        // 요청DTO와 함께 User의 정보만 저장된 응답DTO를 함께 전달
        ManagementResponseDto result = scheduleService.addSchedule(requestDto, responseDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    // 일정 목록 출력
//    @GetMapping()
//    public ResponseEntity<List<ManagementResponseDto>> getScheduleList() {
//        return new ResponseEntity<>(service.getScheduleList(), HttpStatus.OK);
//    }
//
//
//    // 작성자 이름으로 리스트 출력
//    @GetMapping("/user/{user}")
//    public ResponseEntity<List<ManagementResponseDto>> getUserNameList(@PathVariable String user) {
//        return new ResponseEntity<>(service.getUserNameList(user), HttpStatus.OK);
//    }
//
//
//
//    // 특정 날짜 조회
//    @GetMapping("/date/{date}")
//    public ResponseEntity<List<ManagementResponseDto>> getDate(@PathVariable LocalDate date) {
//        return new ResponseEntity<>(service.getDate(date), HttpStatus.OK);
//    }
//
//
//    // 선택한 일정 출력
//    @GetMapping("/{id}")
//    public ResponseEntity<ManagementResponseDto> getSchedule(@PathVariable Long id) {
//        return new ResponseEntity<>(service.getSchedule(id), HttpStatus.OK);
//    }
//
//
//    // 일정 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<ManagementResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ManagementRequestDto dto){
//        return new ResponseEntity<>(service.updateSchedule(id,dto), HttpStatus.OK);
//    }
//
//
//
//    // 일정 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ManagementRequestDto dto){
//        service.deleteSchedule(id, dto.getPassword());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
