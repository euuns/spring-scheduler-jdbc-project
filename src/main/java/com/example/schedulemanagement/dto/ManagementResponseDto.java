package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.Users;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ManagementResponseDto {
    private Long id;
    private Long userId;

    private String title;
    private String content;
    private LocalDate date;

    private String name;
    private String password;


    public ManagementResponseDto(Users users, Schedule schedule){
        this.id = schedule.getId();
        this.userId = users.getId();

        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();

        this.name = users.getName();
        this.password = users.getPassword();
    }

    public ManagementResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.userId = schedule.getUserId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }

    public ManagementResponseDto(Users users){
        this.userId = users.getId();
        this.name = users.getName();
        this.password = users.getPassword();
    }
}
