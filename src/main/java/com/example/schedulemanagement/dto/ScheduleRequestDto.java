package com.example.schedulemanagement.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String user;
    private String content;
    private String password;
}
