package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.Entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final List<Schedule> scheduleList = new ArrayList<>();
    private Schedule schedule = new Schedule();


    // 정보 입력
    @GetMapping("/add")
    public String getInputPage(Model model) {
        model.addAttribute("schedule", schedule);
        return "add-schedule";
    }
    @PostMapping("/add")
    public String addSchedule(@ModelAttribute Schedule schedule) {
        scheduleList.add(schedule);
        return "redirect:/schedule";
    }


    // 일정 목록 출력
    @GetMapping()
    public String getScheduleList(Model model) {
        model.addAttribute("schedules", scheduleList);
        return "schedule-list";
    }
    // 선택한 일정 출력
    @GetMapping("/{id}")
    public String getSchedule(Model model, @PathVariable Long id) {
        schedule = scheduleList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        model.addAttribute("schedule", schedule);
        return "check-schedule";
    }

}
