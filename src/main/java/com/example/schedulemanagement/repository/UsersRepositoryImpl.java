package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UsersResponsDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
public class UsersRepositoryImpl implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // id에 맞는 유저 정보를 반환
    @Override
    public Optional<UsersResponsDto> getUsers(Long id){
        // id에 맞는 값을 가져오는 쿼리 실행 -> where id = ?
        List<UsersResponsDto> query = jdbcTemplate.query("select * from users where id = ?", rowMapper(), id);

        // 반환된 List는 값이 없으면 빈 배열로 반환
        // null을 안전하게 다루기 위한 Optional형태 반환
        return query.stream().findAny();
    }


    @Override
    public UsersResponsDto getUserPassword(Long id){
        List<UsersResponsDto> query = jdbcTemplate.query("select * from users where id = ?", rowMapperToPassword(), id);
        return query.stream().findAny().get();
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
