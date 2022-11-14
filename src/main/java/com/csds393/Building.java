package com.csds393;
import java.util.*;
import java.sql.*;
import java.io.*;

public class Building {
    private String buildingName;
    private HashMap<String,Status> status = new HashMap<String,Status>();
    private String description;
    private List<String> facilities;
    private HashMap<String, Timestamp> timeOfLastEvent;
    private Connection conn;

    public Building(String name, String description, List<String> facilities){
        System.out.println("CONSTRUCTING BUILDING");
        this.buildingName = name;
        this.description = description;
        System.out.println(name);
        this.facilities = facilities;
        try {
        for(String facility : facilities){
            status.put(facility, Status.NOT_BUSY);
        }
        } catch (NullPointerException e){
            
        }
        try {
             openConnection();
             try(Statement stmt = conn.createStatement()) {
                 stmt.executeUpdate("INSERT INTO just_in_case.building(buildingName, description) VALUES ('"
                  + buildingName + "', '" + description + "')"); 
             } catch(SQLException e) {
                 e.printStackTrace();
                 System.out.println("connection failed");
             }
         } catch (FileNotFoundException e1) {
             e1.printStackTrace();
         }        
    }
    
    private void openConnection() throws FileNotFoundException {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        Scanner fr = new Scanner(new File("././untracked.txt"));
        String PASS = fr.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Status getStatus(String buildingUnit){
        return status.get(buildingUnit);
    }

    public String getDescription(){
        return this.description;
    }

    public String getName() {
        return this.buildingName;
    }

    public Status updateBuildingStatus(Status s, String u){
        //calculate new facility status, depending on num of users that selected given status
        refreshBuildingStatus();
        return Status.CLOSED;
    }

    public void refreshBuildingStatus(){
        //check current status
        //check timeOfLastEvent

    }

    public void addBuildingUnit(String u){
        status.put(u, Status.NOT_BUSY);
        try(Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                "INSERT INTO just_in_case.facility(facilityName, status, statusLastUpdated) VALUES ('" 
                + u + "', 'NOT_BUSY', '"+ new Timestamp(System.currentTimeMillis()).toString() +"')"); 
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBuildingUnit(String u){
        status.remove(u);
        try(Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                "DELETE FROM just_in_case.facility WHERE just_in_case.facility.facilityName = '" 
                + u + "'"); 
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "name: " + buildingName + "description: " + description;
    }

}
