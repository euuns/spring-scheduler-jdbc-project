package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private long id;
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
}
