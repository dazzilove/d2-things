package com.dazzilove.dtwothings.api.todaytask.service;

import com.dazzilove.dtwothings.api.todaytask.domain.Task;
import com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb.TaskMongodbRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Task> getTasks() {
        return taskMongodbRepository.findAll();
    }

    @Override
    public void addTask(Task task) {
        task.setDelYn("N");
        taskMongodbRepository.save(task);
    }

    @Override
    public void editTask(Task task) {
        Task savedTask = taskMongodbRepository.findById(task.getId()).orElse(new Task());
        savedTask.setSubject(task.getSubject());
        savedTask.setTitle(task.getTitle());
        savedTask.setDesc(task.getDesc());
        savedTask.setImgSrc(task.getImgSrc());
        savedTask.setDelYn(StringUtils.defaultString(task.getDelYn(), "N").trim());
        taskMongodbRepository.save(savedTask);
    }

    @Override
    public void delTask(Task task) {
        Task savedTask = taskMongodbRepository.findById(task.getId()).orElse(new Task());
        savedTask.setDelYn("Y");
        taskMongodbRepository.save(savedTask);
    }
}
