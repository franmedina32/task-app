package com.example.taskAppFm.service;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.User;
import com.example.taskAppFm.domain.UserRole;
import com.example.taskAppFm.dto.UserDTO;
import com.example.taskAppFm.repository.TaskRepository;
import com.example.taskAppFm.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public User DTOtoUser (UserDTO userDTO){
        return new User(userDTO.getName(), userDTO.getPassword());
    }

    public UserDTO usertoDTO (User user){
        return new UserDTO(user.getName(), user.getPassword());
    }

    public User createUser(UserDTO userDTO){
        User user = DTOtoUser(userDTO);
        user.setScore(0);
        return userRepository.save(user);
    }

    public void deleteUserName(String name) throws ResourceNotFoundException {
        Optional<User> userSearch = userRepository.findByName(name);
        if (userSearch.isPresent()){
            userRepository.delete(userSearch.get());
        }
        else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    public void deleteUserId(Long id) throws ResourceNotFoundException {
        Optional<User> userSearch = userRepository.findById(id);
        if (userSearch.isPresent()){
            userRepository.delete(userSearch.get());
        }
        else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    public User findUserById(Long id) throws ResourceNotFoundException {
        Optional<User> userSearch = userRepository.findById(id);
        if (userSearch.isPresent()){
            return userSearch.get();
        }
        else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    }

    /*public Set<Task> listUserTasks(Long id) throws ResourceNotFoundException{
        Optional<User> userSearch = userRepository.findById(id);
        if (userSearch.isPresent()){
            return userSearch.get().getTasks();
        }
        else {
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
    } */

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public UserDTO findAndCheckUser(UserDTO userDTO) throws ResourceNotFoundException,BadRequestException {
        Optional<User> userSearch = userRepository.findByName(userDTO.getName());
        if (userSearch.isPresent()){
            String passwordDB = userSearch.get().getPassword();
            String passwordProvided = userDTO.getPassword();
            if (passwordDB.equals(passwordProvided)){
                return usertoDTO(userSearch.get());
            }
            else {
                throw new BadRequestException("INCORRECT PASSWORD");
            }
        }
        else {
            throw new ResourceNotFoundException("USER NOT FOUND IN DATABASE");
        }
    }



}
