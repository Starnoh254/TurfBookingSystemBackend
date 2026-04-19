package com.starnoh.turf.bookingsystem.backend.repository;

import com.starnoh.turf.bookingsystem.backend.entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurfRepository extends JpaRepository<Turf, Long> {
}
