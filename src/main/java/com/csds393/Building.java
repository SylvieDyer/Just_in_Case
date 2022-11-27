package com.csds393;
import java.util.*;
import java.sql.*;
import java.io.*;

public class Building {
    private long buildingID;
    private String buildingName;
    private String description;

    public Building() {}

    public Building(long buildingID, String name, String description){
        this.buildingID = buildingID;
        this.buildingName = name;
        this.description = description;
    }

    public Building(String name, String description){
        this.buildingName = name;
        this.description = description;
    }
    

    public long getBuildingID() {
        return this.buildingID;
    }

    public void setBuildingID(long buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public Status updateBuildingStatus(Status s, String u){
    //     //calculate new facility status, depending on num of users that selected given status
    //     refreshBuildingStatus();
    //     return Status.CLOSED;
    // }

    // public void refreshBuildingStatus(){
    //     //check current status
    //     //check timeOfLastEvent

    // }

    // public void addBuildingUnit(String u){
    //     try {
    //         openConnection();
    //         status.put(u, Status.NOT_BUSY);
    //         try(Statement stmt = conn.createStatement()) {
    //             stmt.executeUpdate(
    //                 "INSERT INTO just_in_case.facility(facilityName, status, statusLastUpdated) VALUES ('" 
    //                 + u + "', 'NOT_BUSY', '"+ new Timestamp(System.currentTimeMillis()).toString() +"')"); 
    //         } catch(SQLException e) {
    //             e.printStackTrace();
    //         }
    //         conn.close();
    //     } catch (FileNotFoundException e1) {
    //         // TODO Auto-generated catch block
    //         e1.printStackTrace();
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
        
    // }

    // public void removeBuildingUnit(String u){
    //     try {
    //         openConnection();
    //         status.remove(u);
    //         try(Statement stmt = conn.createStatement()) {
    //             stmt.executeUpdate(
    //                 "DELETE FROM just_in_case.facility WHERE just_in_case.facility.facilityName = '" 
    //                 + u + "'"); 
    //         } catch(SQLException e) {
    //             e.printStackTrace();
    //         }
    //         conn.close();
    //     } catch (FileNotFoundException e1) {
    //         // TODO Auto-generated catch block
    //         e1.printStackTrace();
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
        
    // }

    public String toString() {
        return "name: " + buildingName + "description: " + description;
    }

}
