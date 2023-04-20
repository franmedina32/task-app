package com.example.taskAppFm.domain;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "room_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<User> users;

    public Room(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Room(String name) {
        this.name = name;
    }

    public Room() {
    }

    public void addUser(User user){
        users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Boolean isUserPresent(String userName) {
        Boolean answer = false;
        for (User user: users) {
            if (user.getName().equals(userName)){
                answer = true;
            }
        }
        return answer;
    }
}
