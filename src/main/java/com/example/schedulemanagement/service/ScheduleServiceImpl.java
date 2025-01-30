package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository repository;

    public ScheduleServiceImpl(ScheduleRepository repository){
        this.repository = repository;
    }


    @Override
    public ScheduleResponseDto addSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTitle(), dto.getUser(), dto.getContent(), dto.getPassword());
        return new ScheduleResponseDto(repository.addSchedule(schedule));
    }

    @Override
    public List<ScheduleResponseDto> getScheduleList() {
        return repository.getScheduleList();
    }

    @Override
    public List<ScheduleResponseDto> getUserNameList(String name) {
        return repository.getUserNameList(name);
    }

    @Override
    public List<ScheduleResponseDto> getDate(LocalDate date) {
        return repository.getDate(date);
    }

    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = repository.getSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = repository.getSchedule(id);

        // 암호가 동일하면 내용 변경
        if(schedule.isEqualTo(dto.getPassword())){
            schedule.update(dto);
        }

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = repository.getSchedule(id);

        if(schedule.isEqualTo(password)){
            repository.deleteSchedule(id);
        }
    }
}
