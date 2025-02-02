package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UsersResponsDto;

import java.util.Optional;


public interface UsersRepository {
    Optional<UsersResponsDto> getUsers(Long id);
    Optional<UsersResponsDto> getUserPassword(Long id);

}
