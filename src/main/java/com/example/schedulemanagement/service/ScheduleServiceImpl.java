package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ManagementRequestDto;
import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
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
    public ManagementResponseDto addSchedule(ManagementRequestDto requestDto,Long userId) {

        // Schedule 필드에 값을 저장 -> id만 null
        Schedule schedule = new Schedule(userId, requestDto.getTitle(), requestDto.getContent());
        schedule.setDate(LocalDate.now());

        // Respository에 schedule을 넘겨서 데이터를 DB에 저장
        repository.addSchedule(schedule);

        // schedule을 응답DTO에 넣어 반환
        return new ManagementResponseDto(schedule);
    }



    @Override
    public List<ManagementResponseDto> getScheduleList() {
        return repository.getScheduleList();
    }

//    @Override
//    public List<ManagementResponseDto> getUserNameList(String name) {
//        return repository.getUserNameList(name);
//    }
//
//    @Override
//    public List<ManagementResponseDto> getDate(LocalDate date) {
//        return repository.getDate(date);
//    }
//
//    @Override
//    public ManagementResponseDto getSchedule(Long id) {
//        Schedule schedule = repository.getSchedule(id);
//        return new ManagementResponseDto(schedule);
//    }
//
//    @Override
//    public ManagementResponseDto updateSchedule(Long id, ManagementRequestDto dto) {
//        Schedule schedule = repository.getSchedule(id);
//
//        // 암호가 동일하면 내용 변경
//        if(schedule.isEqualTo(dto.getPassword())){
//            schedule.update(dto);
//        }
//
//        return new ManagementResponseDto(schedule);
//    }
//
//    @Override
//    public void deleteSchedule(Long id, String password) {
//        Schedule schedule = repository.getSchedule(id);
//
//        if(schedule.isEqualTo(password)){
//            repository.deleteSchedule(id);
//        }
//    }
}
