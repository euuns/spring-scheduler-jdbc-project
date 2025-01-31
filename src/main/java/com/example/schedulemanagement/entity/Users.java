package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Long id;
    private String password;
    private String name;
    private String email;

    private LocalDate createDate;
    private LocalDate modifyDate;


    public boolean isEqualTo(String password){
        if(this.password.equals(password)){
            return true;
        }
        else return false;
    }
}
