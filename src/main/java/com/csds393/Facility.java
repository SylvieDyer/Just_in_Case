package com.csds393;

import java.security.Timestamp;

public class Facility {
    String facilityName;
    Status status;
    Timestamp statusLastUpdated; 

    public Facility(String facilityName, Status status, Timestamp statusLastUpdated) {
        this.facilityName = facilityName;
        this.status = status;
        this.statusLastUpdated = statusLastUpdated;
    } 

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getStatusLastUpdated() {
        return statusLastUpdated;
    }

    public void setStatusLastUpdated(Timestamp ts) {
        this.statusLastUpdated = ts;
    }
}
