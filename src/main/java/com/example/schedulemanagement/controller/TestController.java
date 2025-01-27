package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.Entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class TestController {

    private Schedule schedule = new Schedule();


    // 입력한 내용 출력

    @GetMapping("/get")
    public String getSchedule(Model model) {
        model.addAttribute("schedule", schedule);
        return "check-schedule";
    }


    // 정보 입력
    @GetMapping()
    public String getInputPage(Model model) {
        model.addAttribute("schedule", schedule);
        return "add-schedule";
    }

    @PostMapping()
    public String addSchedule(@ModelAttribute Schedule schedule) {
        this.schedule = schedule;
        return "redirect:/schedule/get";
    }

}
