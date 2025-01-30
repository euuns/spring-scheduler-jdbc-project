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


    public LocalDate getDate(){
        return LocalDate.now();
    }

    public void update(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = requestDto.getUser();
        this.password = requestDto.getPassword();
        this.date = new Schedule().getDate();
    }

    public boolean isEqualTo(ScheduleRequestDto requestDto){
        if(this.password.equals(requestDto.getPassword())){
            return true;
        }
        else return false;
    }

}
