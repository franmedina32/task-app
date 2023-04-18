package com.example.taskAppFm.service;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Room;
import com.example.taskAppFm.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room newRoom(Room room){
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) throws ResourceNotFoundException{
        Optional<Room> roomSearch = roomRepository.findById(id);
        if (roomSearch.isPresent()){
            return roomSearch.get();
        }
        else {
            throw new ResourceNotFoundException("ROOM NOT FOUND");
        }
    }

    public Room getRoomByName(String name) throws ResourceNotFoundException{
        Optional<Room> roomSearch = roomRepository.findByName(name);
        if (roomSearch.isPresent()){
            return roomSearch.get();
        }
        else {
            throw new ResourceNotFoundException("ROOM NOT FOUND");
        }
    }

    public List<Room> listRooms(){
        return roomRepository.findAll();
    }

    public void deleteRoomById(Long id) throws ResourceNotFoundException{
        Optional<Room> roomSearch = roomRepository.findById(id);
        if(roomSearch.isPresent()){
            roomRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("CAN'T DELETE A ROOM THAT DOESN'T EXIST");
        }
    }

    public Room modifyRoom(Room room) throws BadRequestException{
        Optional<Room> roomSearch = roomRepository.findByName(room.getName());
        if (roomSearch.isPresent()){
            return roomRepository.save(room);
        }
        else {
            throw new BadRequestException("ROOM DOESN'T EXIST");
        }
    }
}
