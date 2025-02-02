package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UsersResponsDto;

public interface UsersService {

    UsersResponsDto getUsers(Long id);
    boolean isEqualTo(Long id, String password);

}
