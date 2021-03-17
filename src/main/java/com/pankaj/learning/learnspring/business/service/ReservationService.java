package com.pankaj.learning.learnspring.business.service;
import com.pankaj.learning.learnspring.business.domain.RoomReservation;
import com.pankaj.learning.learnspring.data.entity.Guest;
import com.pankaj.learning.learnspring.data.entity.Reservation;
import com.pankaj.learning.learnspring.data.entity.Room;
import com.pankaj.learning.learnspring.data.repository.GuestRepository;
import com.pankaj.learning.learnspring.data.repository.ReservationRepository;
import com.pankaj.learning.learnspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            room.setRoomName(room.getRoomName());
            room.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        }); //
        Iterable<Reservation> reservations = this.reservationRepository.
                findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId()); ///=======<<<<<<<<<<===============
            roomReservation.setDate(date);
            //Get Guest Data
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get(); //return Optional
            roomReservation.setFirstName(guest.getFirstname());
            roomReservation.setLastName(guest.getLastname());
            roomReservation.setGuestId(guest.getGuestId());
        });

        //Convert to List
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }

    public List<Guest> getHotelGuests() {
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guest -> {
            guestList.add(guest);
        });
        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if (o1.getLastname() == o2.getLastname()) {
                    return o1.getFirstname().compareTo(o2.getFirstname());
                }
                return o1.getLastname().compareTo(o2.getLastname());
            }
        });
        return guestList;
    }
}