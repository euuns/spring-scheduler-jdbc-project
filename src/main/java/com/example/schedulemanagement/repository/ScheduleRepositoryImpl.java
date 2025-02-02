package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ManagementResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    // JDBC 연결
    private final JdbcTemplate template;

    public ScheduleRepositoryImpl(DataSource source){
        this.template = new JdbcTemplate(source);
    }



    // DB에 입력받은 데이터를 삽입하는 Repository의 addSchedule 메서드 구현
    @Override
    public ScheduleResponseDto addSchedule(Schedule schdule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        // table이름으로 접근하고 key로 사용할 컬럼명 지정 -> "id"
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        // 컬럼 이름에 맞게 데이터 값을 삽입
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", schdule.getUserId());
        parameters.put("title", schdule.getTitle());
        parameters.put("content", schdule.getContent());
        parameters.put("date", schdule.getDate());

        // 데이터가 저장된 Map을 Sql문으로 전달
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
        // 전달된 sql을 실행하고 key값 반환 -> "id"
        Number key = jdbcInsert.executeAndReturnKey(parameterSource);

        // id가 null인 상태에서 key를 전달해 id값 지정
        schdule.setId(key.longValue());

        // id까지 모두 지정된 schedule 객체 반환
        return new ScheduleResponseDto(schdule);
    }


    @Override
    public List<ManagementResponseDto> getScheduleList() {
        // INNER JOIN을 통해 필요한 값이 들어간 결과 테이블을 호출
        return template.query("SELECT s.id, u.name, s.title, s.content, s.date FROM schedule s JOIN users u ON s.user_id = u.id", scheduleListMapper());
    }


    @Override
    public List<ManagementResponseDto> getUserNameList(String userName) {
        return template.query("SELECT s.id, u.name, s.title, s.content, s.date FROM schedule s JOIN users u ON s.user_id = u.id WHERE u.name = ?", scheduleListMapper(), userName);
    }

    @Override
    public List<ManagementResponseDto> getDateList(LocalDate date) {
        return template.query("SELECT s.id, u.name, s.title, s.content, s.date FROM schedule s JOIN users u ON s.user_id = u.id WHERE s.date = ?", scheduleListMapper(), date);
    }



    // 입력한 id의 글이 존재하는지 확인하고 그 내용을 가져옴
    @Override
    public ManagementResponseDto getSchedule(Long id){
        List<ManagementResponseDto> result = template.query("SELECT s.id, u.name, s.title, s.content, s.date FROM schedule s JOIN users u ON s.user_id = u.id WHERE s.id = ?", scheduleListMapper(), id);
        // result가 있으면 ManagementResponseDto로 반환. 없으면 에외 처리 -> orElseThrow()
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists id = "+id));
    }



    // 글 id를 이용해서 user_id 반환
    @Override
    public ScheduleResponseDto findUserId(Long id){
        List<ScheduleResponseDto> result = template.query("SELECT user_id FROM schedule WHERE id = ?", userIdMapper(), id);
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists id = "+id));
    }



    // 글 수정
    @Override
    public int updateSchedule(Long id, String title, String contnet, LocalDate date){
        return template.update("UPDATE schedule SET title = ?, content = ?, date = ? WHERE id = ?", title, contnet, date, id);
    }


    // 삭제
    @Override
    public int deleteSchedule(Long id){
        return template.update("DELETE FROM schedule WHERE id =?", id);
    }





    private RowMapper<ManagementResponseDto> scheduleListMapper(){

        return new RowMapper<ManagementResponseDto>() {
            @Override
            public ManagementResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ManagementResponseDto(rs.getLong("id"), rs.getString("name"), rs.getString("title"),
                        rs.getString("content"), rs.getDate("date").toLocalDate());
            }
        };
    }

    private RowMapper<ScheduleResponseDto> userIdMapper(){

        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(rs.getLong("user_id"));
            }
        };
    }
}
