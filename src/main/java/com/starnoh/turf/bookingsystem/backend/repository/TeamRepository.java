package com.starnoh.turf.bookingsystem.backend.repository;

import com.starnoh.turf.bookingsystem.backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long > {
    Optional<Team> findByPhoneNumber(String phoneNumber);
}
