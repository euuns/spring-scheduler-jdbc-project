package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ManagementRequestDto;
import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository repository;

    public ScheduleServiceImpl(ScheduleRepository repository){
        this.repository = repository;
    }


    @Override
    public ManagementResponseDto addSchedule(ManagementRequestDto requestDto,Long userId) {

        // Schedule 필드에 값을 저장 -> id만 null
        Schedule schedule = new Schedule(userId, requestDto.getTitle(), requestDto.getContent());
        schedule.setDate(LocalDate.now());

        // Respository에 schedule을 넘겨서 데이터를 DB에 저장
        repository.addSchedule(schedule);

        // schedule을 응답DTO에 넣어 반환
        return new ManagementResponseDto(schedule);
    }


    // 전체 일정 조회
    @Override
    public List<ManagementResponseDto> getScheduleList() {
        return repository.getScheduleList();
    }
    // 입력받은 이름을 기준으로 조회
    @Override
    public List<ManagementResponseDto> getUserNameList(String name) {
        return repository.getUserNameList(name);
    }
    // 입력한 날짜를 기준으로 조회
    @Override
    public List<ManagementResponseDto> getDateList(LocalDate date) {
        return repository.getDateList(date);
    }
    // 선택한 일정 단건 조회
    @Override
    public ManagementResponseDto getSchedule(Long id) {
        List<ManagementResponseDto> schedule = repository.getSchedule(id);
        return schedule.get(0);  //get(0) 추후 수정 예정
    }

    @Override
    public ManagementResponseDto updateSchedule(Long id, ManagementRequestDto dto) {
        List<ManagementResponseDto> schedule = repository.getSchedule(id);
        LocalDate updateDate = LocalDate.now();
        repository.updateSchedule(id, dto.getTitle(), dto.getContent(), updateDate);

        return schedule.get(0);
    }

    @Override
    public void deleteSchedule(Long id) {
        repository.deleteSchedule(id);
    }


    @Override
    public Long findUserId(Long id){
        List<ScheduleResponseDto> userId = repository.findUserId(id);
        return userId.get(0).getUserId();
    }
}
