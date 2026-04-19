package com.starnoh.turf.bookingsystem.backend.repository;

import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking , Long> {
    List<Booking> findByTurfIdAndStartTimeBetween(
            Long turfId,
            LocalDateTime start,
            LocalDateTime end
    );
}
