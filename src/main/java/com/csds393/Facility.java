package com.csds393;

import java.sql.Timestamp;

public class Facility {
    long facilityID;
    String facilityName;
    Status status;
    Timestamp statusLastUpdated; 

    public Facility() {}

    public Facility(String facilityName, Status status, Timestamp statusLastUpdated) {
        this.facilityName = facilityName;
        this.status = status;
        this.statusLastUpdated = statusLastUpdated;
    } 

    public Facility(long facilityID, String facilityName, Status status, Timestamp statusLastUpdated) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.status = status;
        this.statusLastUpdated = statusLastUpdated;
    } 

    public long getFacilityID() {
        return this.facilityID;
    }

    public void setFacilityID(long facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return this.facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getStatusLastUpdated() {
        return this.statusLastUpdated;
    }

    public void setStatusLastUpdated(Timestamp statusLastUpdated) {
        this.statusLastUpdated = statusLastUpdated;
    }

}
