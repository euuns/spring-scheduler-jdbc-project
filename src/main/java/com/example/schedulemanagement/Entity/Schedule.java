package com.example.schedulemanagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class Schedule {
    private long id;
    private static long nextId = 0;

    private String title;
    private String user;
    private String content;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Schedule(){
        // 고유 식별자 자동 생성
        this.id = nextId++;
    }

    public LocalDate getDate(){
        return LocalDate.now();
    }
}
