package com.dazzilove.dtwothings.api.todaytask.service;

import com.dazzilove.dtwothings.api.todaytask.domain.Task;
import com.dazzilove.dtwothings.api.todaytask.domain.TodayTask;
import com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb.TaskMongodbRepository;
import com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb.TodayTaskMongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodayTaskServiceImpl implements TodayTaskService {
    @Autowired
    TaskMongodbRepository taskMongodbRepository;
    @Autowired
    TodayTaskMongodbRepository todayTaskMongodbRepository;

    @Override
    public TodayTask startTodayTask() {
        List<Task> tasks = taskMongodbRepository.findAll().stream().filter(task -> !task.isDelete()).collect(Collectors.toList());
        TodayTask todayTask = new TodayTask();
        todayTask.setStartedAt(new Date());
        todayTask.setTasks(tasks);
        todayTaskMongodbRepository.save(todayTask);
        return todayTask;
    }
}
