package com.starnoh.turf.bookingsystem.backend.service;

import com.starnoh.turf.bookingsystem.backend.dto.BookingRequest;
import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import com.starnoh.turf.bookingsystem.backend.entity.Team;
import com.starnoh.turf.bookingsystem.backend.entity.Turf;
import com.starnoh.turf.bookingsystem.backend.repository.BookingRepository;
import com.starnoh.turf.bookingsystem.backend.repository.TeamRepository;
import com.starnoh.turf.bookingsystem.backend.repository.TurfRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TurfRepository turfRepository;
    private final TeamRepository teamRepository;

    public BookingService(BookingRepository bookingRepository, TurfRepository turfRepository, TeamRepository teamRepository) {
        this.bookingRepository = bookingRepository;
        this.turfRepository = turfRepository;
        this.teamRepository = teamRepository;
    }

    public Booking createBooking(BookingRequest request) {

        Turf turf = turfRepository.findById(request.getTurfId())
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        Team team = teamRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        Team savedTeam = new Team();
        savedTeam.setTeamName(request.getTeamName());
        savedTeam.setPhoneNumber(request.getPhoneNumber());
        teamRepository.save(savedTeam);

        Booking booking = new Booking();
        booking.setTurf(turf);
        booking.setTeam(team);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setAmount(2000L);
        return bookingRepository.save(booking);
    }

}
