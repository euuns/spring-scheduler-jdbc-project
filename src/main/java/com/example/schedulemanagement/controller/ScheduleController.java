package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ManagementRequestDto;
import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.UsersResponsDto;
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
        UsersResponsDto users = usersService.getUsers(requestDto.getUserId());

        // 응답DTO에 나머지 Schedule을 담아서 반환하기 위해 Service를 호출해 로직 수행
        // 요청DTO와 함께 user의 id 함께 전달
        ManagementResponseDto result = scheduleService.addSchedule(requestDto, users.getId());

        // 남은 값 user이름도 저장
        result.setName(users.getName());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 일정 목록 출력
    @GetMapping()
    public ResponseEntity<List<ManagementResponseDto>> getScheduleList() {
        return new ResponseEntity<>(scheduleService.getScheduleList(), HttpStatus.OK);
    }


    // 작성자 이름으로 리스트 출력
    @GetMapping("/user/{user}")
    public ResponseEntity<List<ManagementResponseDto>> getUserNameList(@PathVariable String user) {
        return new ResponseEntity<>(scheduleService.getUserNameList(user), HttpStatus.OK);
    }



    // 특정 날짜 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ManagementResponseDto>> getDate(@PathVariable LocalDate date) {
        return new ResponseEntity<>(scheduleService.getDateList(date), HttpStatus.OK);
    }


    // 선택한 일정 출력
    @GetMapping("/{id}")
    public ResponseEntity<ManagementResponseDto> getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.getSchedule(id), HttpStatus.OK);
    }


    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ManagementResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ManagementRequestDto dto){
        // 글 id를 통해 userId를 반환
        Long userId = scheduleService.findUserId(id);

        // userID를 이용해 password가 맞는지 확인
        usersService.isEqualTo(userId, dto.getPassword());

        // 위에서 예외가 나오지 않으면 일정을 수정
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }


    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ManagementRequestDto dto){
        Long userId = scheduleService.findUserId(id);
        usersService.isEqualTo(userId, dto.getPassword());

        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
