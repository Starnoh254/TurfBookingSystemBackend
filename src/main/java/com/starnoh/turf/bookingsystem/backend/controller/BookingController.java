package com.starnoh.turf.bookingsystem.backend.controller;

import com.starnoh.turf.bookingsystem.backend.dto.AvailabilityResponse;
import com.starnoh.turf.bookingsystem.backend.dto.BookingRequest;
import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import com.starnoh.turf.bookingsystem.backend.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/availability")
    public AvailabilityResponse getAvailability(
            @RequestParam Long turfId,
            @RequestParam LocalDate date
            ){
        return bookingService.getAvailability(turfId,date);
    }

    @PostMapping("/book")
    public Booking book(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
}
