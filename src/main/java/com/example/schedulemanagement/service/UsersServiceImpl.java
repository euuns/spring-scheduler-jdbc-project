package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UsersResponsDto;
import com.example.schedulemanagement.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }



    @Override
    public UsersResponsDto getUsers(Long id) {
        Optional<UsersResponsDto> users = usersRepository.getUsers(id);

        // users가 비어있으면 예외 처리
        if(users.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist user id = " + id);
        }
        return users.get();
    }

    @Override
    public boolean isEqualTo(Long id, String password){
        Optional<UsersResponsDto> user = usersRepository.getUserPassword(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(user.get().getPassword().equals(password)){
            return true;
        }
        else return false;
    }
}
