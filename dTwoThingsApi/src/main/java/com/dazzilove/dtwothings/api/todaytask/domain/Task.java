package com.dazzilove.dtwothings.api.todaytask.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Task {
    @Id
    private String id;
    private String subject;
    private String title;
    private String desc;
    private String imgSrc;
    private String state;
}
