package com.example.schedulemanagement.entity;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Setter
    private long id;

    private String title;
    private String user;
    private String content;
    private String password;

    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;



    public Schedule(String title, String user, String content, String password) {
        this.title = title;
        this.user = user;
        this.content = content;
        this.password = password;
    }


    public void update(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = requestDto.getUser();
        this.password = requestDto.getPassword();
        this.date = LocalDate.now();
    }

    public boolean isEqualTo(String password){
        if(this.password.equals(password)){
            return true;
        }
        else return false;
    }

}
