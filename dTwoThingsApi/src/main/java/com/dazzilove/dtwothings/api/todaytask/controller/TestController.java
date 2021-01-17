package com.dazzilove.dtwothings.api.todaytask.controller;

import com.dazzilove.dtwothings.api.todaytask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TaskService taskService;

    @GetMapping("/api/todayTask/test")
    public String test() {
        return "test";
    }

    @GetMapping("/api/todayTask/add")
    public String add() {
        taskService.addTestTask();
        return "success";
    }

}
