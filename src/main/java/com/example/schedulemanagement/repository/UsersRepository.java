package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UsersResponsDto;


public interface UsersRepository {
    UsersResponsDto getUsers(Long id);
    UsersResponsDto getUserPassword(Long id);

}
