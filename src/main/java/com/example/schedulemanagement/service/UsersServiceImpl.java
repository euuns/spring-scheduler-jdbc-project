package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UsersResponsDto;
import com.example.schedulemanagement.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }



    @Override
    public UsersResponsDto getUsers(Long id) {
        return usersRepository.getUsers(id);
    }

    @Override
    public boolean isEqualTo(Long id, String password){
        UsersResponsDto user = usersRepository.getUserPassword(id);

        // 비밀번호가 일치하지 않을 경우 예외 처리
        if(user.getPassword().equals(password)){
            return true;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Password does not match.");
    }
}
