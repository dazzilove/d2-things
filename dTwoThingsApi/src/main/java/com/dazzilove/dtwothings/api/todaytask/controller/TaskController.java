package com.dazzilove.dtwothings.api.todaytask.controller;

import com.dazzilove.dtwothings.api.todaytask.controller.dto.TaskDto;
import com.dazzilove.dtwothings.api.todaytask.domain.Task;
import com.dazzilove.dtwothings.api.todaytask.domain.TodayTask;
import com.dazzilove.dtwothings.api.todaytask.service.TaskService;
import com.dazzilove.dtwothings.api.todaytask.service.TodayTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    TodayTaskService todayTaskService;

    @GetMapping("/api/todayTask/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        try {
            return new ResponseEntity(taskService.getTasks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/todayTask/task")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {

        Task task = new Task();
        task.setSubject(taskDto.getSubject());
        task.setTitle(taskDto.getTitle());
        task.setDesc(taskDto.getDesc());
        task.setImgSrc(taskDto.getImgSrc());

        try {
            taskService.addTask(task);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/todayTask/task/edit")
    public ResponseEntity<String> editTask(@RequestBody TaskDto taskDto) {

        Task task = new Task();
        task.setId(taskDto.getId());
        task.setSubject(taskDto.getSubject());
        task.setTitle(taskDto.getTitle());
        task.setDesc(taskDto.getDesc());
        task.setImgSrc(taskDto.getImgSrc());
        task.setDelYn(taskDto.getDelYn());

        try {
            taskService.editTask(task);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/todayTask/task/delete")
    public ResponseEntity<String> delTask(@RequestBody TaskDto taskDto) {

        Task task = new Task();
        task.setId(taskDto.getId());

        try {
            taskService.delTask(task);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/todayTask/start")
    public ResponseEntity<TodayTask> startTodayTask() {
        try {
            return new ResponseEntity(todayTaskService.startTodayTask(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
