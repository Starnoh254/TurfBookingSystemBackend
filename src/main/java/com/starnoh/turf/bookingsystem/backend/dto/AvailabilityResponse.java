package com.starnoh.turf.bookingsystem.backend.dto;

import java.util.List;

public class AvailabilityResponse {
    private List<String> availableSlots;

    public AvailabilityResponse(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }
}
