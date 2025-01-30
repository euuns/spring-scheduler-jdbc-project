package com.example.schedulemanagement.entity;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @Setter
    private Long id;

    private String title;
    private String content;

    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;



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
