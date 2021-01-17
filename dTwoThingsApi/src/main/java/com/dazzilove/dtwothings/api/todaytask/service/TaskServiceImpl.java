package com.dazzilove.dtwothings.api.todaytask.service;

import com.dazzilove.dtwothings.api.todaytask.domain.Task;
import com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb.TaskMongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMongodbRepository taskMongodbRepository;

    @Override
    public void addTestTask() {
        Task task = new Task();
        task.setSubject("subject");
        task.setTitle("title");
        task.setDesc("desc");
        taskMongodbRepository.save(task);
    }
}
