package com.starnoh.turf.bookingsystem.backend.util;

public class ErrorResponse {

    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;

    public ErrorResponse(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public String getType() { return type; }
    public String getTitle() { return title; }
    public int getStatus() { return status; }
    public String getDetail() { return detail; }
    public String getInstance() { return instance; }
}
