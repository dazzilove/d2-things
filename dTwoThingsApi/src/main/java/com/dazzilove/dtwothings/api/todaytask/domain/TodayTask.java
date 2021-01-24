package com.dazzilove.dtwothings.api.todaytask.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class TodayTask {
    @Id String id;
    Date startedAt;
    Date endedAt;
    List<Task> tasks;
}
