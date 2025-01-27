package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.Entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private Schedule schedule = new Schedule();


    // 입력한 내용 출력
    @GetMapping("/output")
    public String getTest(Model model) {
        model.addAttribute("title", schedule.getTitle());
        model.addAttribute("content", schedule.getContent());
        model.addAttribute("date", schedule.getDate());

        return "output-test";
    }


    // 정보 입력
    @GetMapping("/input")
    public String getInputPage(Model model) {
        model.addAttribute("schedule", schedule);
        return "create-test";
    }

    @PostMapping("/input")
    public String postTest(@RequestParam("title") String title,
                           @RequestParam("content") String content) {

        schedule.setTitle(title);
        schedule.setContent(content);

        return "redirect:/test/output";
    }

}
