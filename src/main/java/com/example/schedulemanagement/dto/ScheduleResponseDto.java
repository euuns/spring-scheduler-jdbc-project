package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDate date;


    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.userId = schedule.getUserId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }


    public ScheduleResponseDto(Long id, Long user_id, String title, String content, LocalDate date){
        this.id = id;
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.date = date;

    }

}
