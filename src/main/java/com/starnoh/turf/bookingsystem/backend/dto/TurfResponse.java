package com.starnoh.turf.bookingsystem.backend.dto;

public class TurfResponse {


    private Long turfId;
    private String turfName;
    private String turfDesc;
    private String turfLocation;
    private Long turfPriceRate;
    private String phoneNumber;


    public void setTurfId(Long turfId) {
        this.turfId = turfId;
    }

    public void setTurfName(String turfName) {
        this.turfName = turfName;
    }

    public void setTurfDesc(String turfDesc) {
        this.turfDesc = turfDesc;
    }

    public void setTurfLocation(String turfLocation) {
        this.turfLocation = turfLocation;
    }

    public void setTurfPriceRate(Long turfPriceRate) {
        this.turfPriceRate = turfPriceRate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTurfId() {
        return turfId;
    }

    public String getTurfName() {
        return turfName;
    }

    public String getTurfDesc() {
        return turfDesc;
    }

    public String getTurfLocation() {
        return turfLocation;
    }

    public Long getTurfPriceRate() {
        return turfPriceRate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
