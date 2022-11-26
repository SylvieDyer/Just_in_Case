package com.csds393;
import java.util.*;
import java.sql.*;
import java.io.*;

public class Building {
    private String buildingName;
    //private HashMap<String,Status> status = new HashMap<String,Status>();
    private String description;
    // private List<String> facilities;
    // private HashMap<String, Timestamp> timeOfLastEvent;

    public Building(String name, String description, List<String> facilities){
        System.out.println("CONSTRUCTING BUILDING");
        this.buildingName = name;
        this.description = description;
        System.out.println(name);
        //this.facilities = facilities;
        // try {
        // for(String facility : facilities){
        //     status.put(facility, Status.NOT_BUSY);
        // }
        // } catch (NullPointerException e){
            
        // }       
    }

    public void setName(String name) {
        this.buildingName = name;
    }

    public String getName() {
        return this.buildingName;
    }

    // public Status getStatus(String buildingUnit){
    //     return status.get(buildingUnit);
    // }

    public void setDescription(String description_) {
        this.description = description_;
    }
    
    public String getDescription(){
        return this.description;
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
