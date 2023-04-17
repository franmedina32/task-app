package com.example.taskAppFm.service;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.TaskState;
import com.example.taskAppFm.domain.User;
import com.example.taskAppFm.dto.TaskDTO;
import com.example.taskAppFm.repository.TaskRepository;
import com.example.taskAppFm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public Task DTOtoTask(TaskDTO taskDTO) throws ResourceNotFoundException{
        if (taskDTO.getUser_id() != null){
            Optional<User> user = userRepository.findById(taskDTO.getUser_id());
            if (user.isPresent()){
               User actualUser = user.get();
               return new Task(taskDTO.getName(), taskDTO.getPoints(), taskDTO.getDateTime(), taskDTO.getTaskState(),actualUser);
            }
            else {
                throw new ResourceNotFoundException("USER NOT FOUND");
            }
        }
        else {
            return new Task(taskDTO.getName(), taskDTO.getPoints(), taskDTO.getDateTime(), taskDTO.getTaskState());
        }
    }



    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(TaskDTO taskDTO) throws ResourceNotFoundException{
        return taskRepository.save(this.DTOtoTask(taskDTO));
    }

    public Task setResponsible(TaskDTO taskDTO) throws ResourceNotFoundException, BadRequestException {
        Optional<Task> taskSearch = taskRepository.findByName(taskDTO.getName());
        if (taskSearch.isPresent()){
            Optional<User> user = userRepository.findById(taskDTO.getUser_id());
            if (user.isPresent()){
                taskSearch.get().setUser(user.get());
                return taskRepository.save(taskSearch.get());
            }
            else {
                throw new BadRequestException("USER NOT FOUND");
            }
        }
        else {
            throw new ResourceNotFoundException("TASK NOT FOUND");
        }
    }



    public Task modifyDate(Task task) throws  ResourceNotFoundException{
        Optional<Task> taskSearch = taskRepository.findById(task.getId());
        if (taskSearch.isPresent()){
            try {
                taskSearch.get().setDateTime(task.getDateTime());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return taskSearch.get();
        }
        else {
            throw new ResourceNotFoundException("THE TASK DOESN'T EXIST");
        }
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }

    public void deleteTaskById(Long id) throws ResourceNotFoundException{
        Optional<Task> taskSearch = taskRepository.findById(id);
            if (taskSearch.isPresent()){
                taskRepository.delete(taskSearch.get());
            }
            else {
                throw new ResourceNotFoundException("TASK NOT FOUND");
            }
        }


    public void taskDone(Long id){
        Optional<Task> taskSearch = taskRepository.findById(id);
        if (taskSearch.isPresent()){
            Integer valor = taskSearch.get().getPoints();
            taskSearch.get().setTaskState(TaskState.DONE);
            User user = taskSearch.get().getUser();
            user.setScore(user.getScore()+valor);
        }
    }

    public Task findTaskByID(Long id) throws ResourceNotFoundException{
        Optional<Task> taskSearch = taskRepository.findById(id);
        if (taskSearch.isPresent()){
            return taskSearch.get();
        }
        else {
            throw new ResourceNotFoundException("TASK NOT FOUND");
        }
    }

    public List<Task> listTasks(){
        return taskRepository.findAll();
    }


}
