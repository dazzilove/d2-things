package com.dazzilove.dtwothings.api.todaytask.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Task {
    @Id
    private String id;
    private String subject;
    private String title;
    private String desc;
    private String imgSrc;
    private String state;
    private String delYn;
    private Date doneAt;

    public boolean isDelete() {
        return "Y".equals(this.delYn);
    }
}
