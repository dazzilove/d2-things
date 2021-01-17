package com.dazzilove.dtwothings.api.todaytask.controller.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String subject;
    private String title;
    private String desc;
    private String imgSrc;
    private String state;
    private String delYn;
}
