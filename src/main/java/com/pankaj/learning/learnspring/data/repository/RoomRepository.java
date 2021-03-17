package com.pankaj.learning.learnspring.data.repository;

import com.pankaj.learning.learnspring.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository  extends CrudRepository<Room, Long> {
    
}
