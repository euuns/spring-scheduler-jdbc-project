package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.UsersResponsDto;
import com.example.schedulemanagement.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;


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


    // 이메일 형식 확인
    private boolean emailValidator(String email){
        // 이메일 정규 표현식
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Pattern.matches를 이용해 확인하고, 형식과 맞지 않으면 예외 처리
        if(Pattern.matches(regex, email)){
            return true;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email format is incorrect.");
    }
}
