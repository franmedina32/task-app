package com.example.taskAppFm.dto;

public class RoomDTO {
    private String name;
    private Long user_id;

    public RoomDTO(String name, Long user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public RoomDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
