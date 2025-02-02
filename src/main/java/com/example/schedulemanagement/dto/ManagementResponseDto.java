package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ManagementResponseDto {
    private Long id;
    @Setter
    private String name;
    private String title;
    private String content;
    private LocalDate date;



    public ManagementResponseDto(ScheduleResponseDto schedule, String name){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
        this.name = name;
    }


    public ManagementResponseDto(String name){
        this.name = name;
    }


    public ManagementResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }

    public ManagementResponseDto(long id, String name, String title, String content, LocalDate date) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
