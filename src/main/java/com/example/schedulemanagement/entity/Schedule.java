package com.example.schedulemanagement.entity;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private long id;
    private String title;
    private String user;
    private String content;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public void update(ScheduleRequestDto requestDto){
        this.user = requestDto.getUser();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public static LocalDate getDate(){
        return LocalDate.now();
    }

}
