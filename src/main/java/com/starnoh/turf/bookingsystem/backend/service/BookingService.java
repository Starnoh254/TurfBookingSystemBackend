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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        // 1. Get the existing team or create/save a new one in one flow
        Team savedTeam = teamRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> {
                    Team newTeam = new Team();
                    newTeam.setTeamName(request.getTeamName());
                    newTeam.setPhoneNumber(request.getPhoneNumber());
                    return teamRepository.save(newTeam);
                });

        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                request.getTurfId(),
                request.getStartTime(),
                request.getEndTime()
        );

        if(!conflicts.isEmpty()) throw new RuntimeException("Turf already booked for this time slot");



        // 2. Proceed with booking (savedTeam is now guaranteed to be a Team object)
        Booking booking = new Booking();
        booking.setTurf(turf);
        booking.setTeam(savedTeam);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());

        Duration duration = Duration.between(request.getStartTime(), request.getEndTime());
        long minutes = duration.toMinutes();
        double hours = minutes / 60.0;

        double amount = turf.getTurfPriceRate() * hours;
        booking.setAmount((long) amount);

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

    private boolean isFree(TimeSlot slot, List<Booking> bookings, LocalDate date) {

        LocalDateTime slotStart = LocalDateTime.of(date, slot.getStart());
        LocalDateTime slotEnd = LocalDateTime.of(date, slot.getEnd());

        return bookings.stream().noneMatch(booking ->
                slotStart.isBefore(booking.getEndTime()) &&
                        booking.getStartTime().isBefore(slotEnd)
        );
    }


    public AvailabilityResponse getAvailability(Long turfId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23,59,59);

        if(turfId == null){
            throw new RuntimeException("Turf Id not provided");
        }
        List<TimeSlot> allSlots = generateSlots();
        List<Booking> bookings =
                bookingRepository.findBookingsForDay(turfId, startOfDay , endOfDay);

        List<String> available = allSlots.stream()
                .filter(slot -> isFree(slot, bookings , date))
                .map(TimeSlot::toString)
                .toList();

//        available.forEach(System.out::println);

        return new AvailabilityResponse(available);
    }

}
