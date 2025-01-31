package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
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


    public ManagementResponseDto(Long id, String name, String password){
        this.userId = id;
        this.name = name;
        this.password = password;
    }



    // 생성한 메모와 작성자 정보를 반환하기 위한 메서드
    // userId, name, password만 저장된 응답DTO에 나머지 값을 저장해주는 역할
    public ManagementResponseDto addScheduleToUser(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();

        return this;
    }
}
