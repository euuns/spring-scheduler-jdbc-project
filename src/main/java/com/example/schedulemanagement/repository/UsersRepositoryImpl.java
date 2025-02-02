package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UsersResponsDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UsersRepositoryImpl implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // id에 맞는 유저 정보를 반환
    @Override
    public UsersResponsDto getUsers(Long id){
        // id에 맞는 값을 가져오는 쿼리 실행 -> where id = ?
        List<UsersResponsDto> query = jdbcTemplate.query("select id, name from users where id = ?", rowMapper(), id);

        return query.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists user_id = "+id));
    }


    @Override
    public UsersResponsDto getUserPassword(Long id){
        List<UsersResponsDto> query = jdbcTemplate.query("select password from users where id = ?", rowMapperToPassword(), id);

        // 결과를 찾으면 반환. 없으면 예외 처리.
        return query.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists user_id = "+id));
    }




    private RowMapper<UsersResponsDto> rowMapper() {
        return new RowMapper<UsersResponsDto>() {
            @Override
            public UsersResponsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // DB에서 쿼리문을 통해 결과집합 ResultSet 반환
                // ResultSet을 통해 가져온 DB의 값을 Users에 담아서 List로 반환
                return new UsersResponsDto(rs.getLong("id"), rs.getString("name"));
            }
        };
    }


    private RowMapper<UsersResponsDto> rowMapperToPassword() {
        return new RowMapper<UsersResponsDto>() {
            @Override
            public UsersResponsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UsersResponsDto(rs.getString("password"));
            }
        };
    }
}
