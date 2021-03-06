
package com.pankaj.learning.learnspring.web;

import com.pankaj.learning.learnspring.data.entity.Room;
import com.pankaj.learning.learnspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
   @GetMapping
    public Iterable<Room> getRooms()
   {
       return roomRepository.findAll();
   }
}

