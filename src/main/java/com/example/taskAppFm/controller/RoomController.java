package com.example.taskAppFm.controller;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Room;
import com.example.taskAppFm.dto.RoomDTO;
import com.example.taskAppFm.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/new")
    public Room newRoom(@RequestBody Room room){
        return roomService.newRoom(room);
    }

    @GetMapping("/id/{id}")
    public Room findRoomById(@PathVariable Long id) throws ResourceNotFoundException {
        return roomService.getRoomById(id);
    }

    @GetMapping("/name/{name}")
    public Room findRoomByName(@PathVariable String name) throws ResourceNotFoundException {
        return roomService.getRoomByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoomById(@PathVariable Long id) throws ResourceNotFoundException {
        roomService.deleteRoomById(id);
    }

    @PutMapping("/modify")
    public Room modifyRoom(@RequestBody Room room) throws BadRequestException {
        return roomService.modifyRoom(room);
    }

    @GetMapping("/listRooms")
    public List<Room> listRooms(){
        return roomService.listRooms();
    }

    @PutMapping("/addUser")
    public Room addUserToRoom(@RequestBody RoomDTO roomDTO) throws ResourceNotFoundException{
        return roomService.addUserToRoom(roomDTO);
    }


}
