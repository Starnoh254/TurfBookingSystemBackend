package com.starnoh.turf.bookingsystem.backend.dto;

public class TurfRequest {
    private String turfName;
    private String turfDesc;
    private String turfLocation;
    private Long turfPriceRate;
    private String phoneNumber;

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
}
