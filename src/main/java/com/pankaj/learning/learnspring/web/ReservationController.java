package com.pankaj.learning.learnspring.web;

import com.pankaj.learning.learnspring.data.entity.Reservation;
import com.pankaj.learning.learnspring.data.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @GetMapping
    public Iterable<Reservation> getReservations()
    {
        return reservationRepository.findAll();
    }
}


