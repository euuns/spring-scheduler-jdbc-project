package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.entity.Users;

import java.util.Optional;


public interface UsersRepository {
    Optional<ManagementResponseDto> getUsers(Long id);


}
