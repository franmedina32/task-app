package com.example.taskAppFm.controller;

import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.User;
import com.example.taskAppFm.dto.UserDTO;
import com.example.taskAppFm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
       return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserId(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteUserId(id);
    }

    @GetMapping("/{id}")
    public User getUserId(@PathVariable Long id) throws ResourceNotFoundException {
        return userService.findUserById(id);
    }

    /*@GetMapping("listTasks/{id}")
    public Set<Task> listTasks(@PathVariable Long id) throws ResourceNotFoundException{
        return userService.listUserTasks(id);
    } */

    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userService.findAndCheckUser(userDTO));
    }

    @PostMapping("/name")
    public ResponseEntity<User> getUserByName(@RequestBody UserDTO userDTO) throws Exception{
        return ResponseEntity.ok(userService.findUserByName(userDTO));
    }
}
