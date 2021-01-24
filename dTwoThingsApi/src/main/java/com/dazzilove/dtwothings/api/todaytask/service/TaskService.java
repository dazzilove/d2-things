package com.dazzilove.dtwothings.api.todaytask.service;

import com.dazzilove.dtwothings.api.todaytask.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    void addTask(Task task);

    void editTask(Task task);

    void delTask(Task task);
}
