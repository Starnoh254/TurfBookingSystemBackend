package com.starnoh.turf.bookingsystem.backend.repository;

import com.starnoh.turf.bookingsystem.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking , Long> {
    List<Booking> findByTurfIdAndStartTimeBetween(
            Long turfId,
            LocalDateTime start,
            LocalDateTime end
    );

//    List<Booking> findByTurfIdAndDate(Long turfId, LocalDate date);

    @Query("""
SELECT b FROM Booking b
WHERE b.turf.id = :turfId
AND b.startTime < :endOfDay
AND b.endTime > :startOfDay
""")
    List<Booking> findBookingsForDay(
            Long turfId,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );
}
