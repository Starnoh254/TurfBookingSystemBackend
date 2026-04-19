package com.starnoh.turf.bookingsystem.backend.controller;

import com.starnoh.turf.bookingsystem.backend.dto.AvailabilityResponse;
import com.starnoh.turf.bookingsystem.backend.dto.BookingRequest;
import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import com.starnoh.turf.bookingsystem.backend.service.BookingService;
import com.starnoh.turf.bookingsystem.backend.util.ApiResponse;
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
    public ApiResponse<AvailabilityResponse> getAvailability(
            @RequestParam Long turfId,
            @RequestParam LocalDate date
            ){

        AvailabilityResponse response = bookingService.getAvailability(turfId,date);
        return ApiResponse.success(response);
    }

    @PostMapping("/book")
    public ApiResponse<Booking> book(@RequestBody BookingRequest request) {

        Booking bookingResponse = bookingService.createBooking(request);
        return ApiResponse.success(bookingResponse);
    }
}
