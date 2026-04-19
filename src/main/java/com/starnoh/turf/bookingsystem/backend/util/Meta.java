package com.starnoh.turf.bookingsystem.backend.util;

import java.time.Instant;

public class Meta {
    private String timestamp;
    private String version;

    public Meta() {
        this.timestamp = Instant.now().toString();
        this.version = "1.0";
    }

    public String getTimestamp() { return timestamp; }
    public String getVersion() { return version; }
}
