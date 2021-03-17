
package com.pankaj.learning.learnspring.web;

import com.pankaj.learning.learnspring.data.entity.Guest;
import com.pankaj.learning.learnspring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    GuestRepository  guestRepository;

    @GetMapping
    public Iterable<Guest> getGuests()
    {
        return guestRepository.findAll();
    }

}

