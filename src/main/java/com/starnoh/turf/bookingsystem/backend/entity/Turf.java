package com.starnoh.turf.bookingsystem.backend.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String turfName;
    private String turfDesc;
    private String turfLocation;
    private Long turfPriceRate;
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurfName() {
        return turfName;
    }

    public void setTurfName(String turfName) {
        this.turfName = turfName;
    }

    public String getTurfDesc() {
        return turfDesc;
    }

    public void setTurfDesc(String turfDesc) {
        this.turfDesc = turfDesc;
    }

    public String getTurfLocation() {
        return turfLocation;
    }

    public void setTurfLocation(String turfLocation) {
        this.turfLocation = turfLocation;
    }

    public Long getTurfPriceRate() {
        return turfPriceRate;
    }

    public void setTurfPriceRate(Long turfPriceRate) {
        this.turfPriceRate = turfPriceRate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
