package com.example.schedulemanagement.entity;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Setter
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDate date;



    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void update(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.date = LocalDate.now();
    }

}
