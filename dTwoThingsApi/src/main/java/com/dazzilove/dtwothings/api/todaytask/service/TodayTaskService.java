package com.dazzilove.dtwothings.api.todaytask.service;

import com.dazzilove.dtwothings.api.todaytask.domain.TodayTask;

public interface TodayTaskService {
    TodayTask startTodayTask();

    void updateTodayTask(TodayTask todayTask);

    TodayTask getTodayTask(String id);
}
