package com.example.taskAppFm.controller;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.TaskState;
import com.example.taskAppFm.dto.TaskDTO;
import com.example.taskAppFm.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
        return taskService.findTaskByID(id);
    }

    @GetMapping("/list")
    public List<Task> listTasks(){
        return taskService.listTasks();
    }

    @PostMapping("/new")
    public ResponseEntity<Task> newTask(@RequestBody TaskDTO taskDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) throws ResourceNotFoundException {
        taskService.deleteTaskById(id);
    }

    @PutMapping("/setResponsible")
    public Task setResponsible(@RequestBody TaskDTO taskDTO) throws ResourceNotFoundException, BadRequestException{
        return taskService.setResponsible(taskDTO);
    }

    @PutMapping("/setDate")
    public Task setDate(@RequestBody Task task) throws ResourceNotFoundException{
        return taskService.modifyDate(task);
    }

    @PutMapping("/taskDone/{id}")
    public void setTaskDone(@PathVariable Long id) throws BadRequestException{
         taskService.taskDone(id);
    }

    @PostMapping("/name")
    public ResponseEntity<Task> getTaskByName(@RequestBody TaskDTO taskDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.findTaskByName(taskDTO));
    }


}
