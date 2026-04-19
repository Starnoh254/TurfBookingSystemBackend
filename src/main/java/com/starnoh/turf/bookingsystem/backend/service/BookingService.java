package com.starnoh.turf.bookingsystem.backend.service;

import com.starnoh.turf.bookingsystem.backend.dto.AvailabilityResponse;
import com.starnoh.turf.bookingsystem.backend.dto.BookingRequest;
import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import com.starnoh.turf.bookingsystem.backend.entity.Team;
import com.starnoh.turf.bookingsystem.backend.entity.Turf;
import com.starnoh.turf.bookingsystem.backend.repository.BookingRepository;
import com.starnoh.turf.bookingsystem.backend.repository.TeamRepository;
import com.starnoh.turf.bookingsystem.backend.repository.TurfRepository;
import com.starnoh.turf.bookingsystem.backend.util.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<TimeSlot> generateSlots() {

        List<TimeSlot> slots = new ArrayList<>();

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        while (start.isBefore(end)) {
            LocalTime next = start.plusHours(1);

            slots.add(new TimeSlot(start, next));

            start = next;
        }

        return slots;
    }

    private boolean isFree(TimeSlot slot, List<Booking> bookings) {

        return bookings.stream().noneMatch(booking ->
                booking.getStartTime().equals(slot.getStart())
        );
    }


    public AvailabilityResponse getAvailability(Long turfId, LocalDate date) {
        List<TimeSlot> allSlots = generateSlots();
        List<Booking> bookings =
                bookingRepository.findByTurfIdAndDate(turfId, date);

        List<String> available = allSlots.stream()
                .filter(slot -> isFree(slot, bookings))
                .map(TimeSlot::toString)
                .toList();

        return new AvailabilityResponse(available);
    }

}
