package com.example.taskAppFm.dto;

import com.example.taskAppFm.domain.TaskState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class TaskDTO {
    private String name;
    private Integer points;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TaskState taskState;
    private Long user_id;

    public TaskDTO() {
    }

    public TaskDTO(String name, Integer points, LocalDateTime dateTime, TaskState taskState, Long user_id) {
        this.name = name;
        this.points = points;
        this.dateTime = dateTime;
        this.taskState = taskState;
        this.user_id = user_id;
    }

    public TaskDTO(String name, Integer points, LocalDateTime dateTime, TaskState taskState) {
        this.name = name;
        this.points = points;
        this.dateTime = dateTime;
        this.taskState = taskState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
