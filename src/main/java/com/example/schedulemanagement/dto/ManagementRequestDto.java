package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ManagementRequestDto {
    private Long id;
    private Long userId;

    private String title;
    private String content;
    private LocalDate date;

    private String password;
}
