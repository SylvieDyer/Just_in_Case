package backend;
import java.util.*;
import java.sql.*;

public class Building {
    private String buildingName;
    private HashMap<String,Status> status = new HashMap<String,Status>();
    private String description;
    private HashMap<String, Timestamp> timeOfLastEvent;
    private Connection conn;

    public Building(String name, String description, List<String> facilities){
        this.buildingName = name;
        this.description = description;
        for(String facility : facilities){
            status.put(facility, Status.NOT_BUSY);
        }
        openConnection();

        try(Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO just_in_case.building VALUES ('" + 1 + "', '" + buildingName + "', '" + description + "')"); 
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void openConnection() {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        String PASS = "";
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
    }

    public void removeBuildingUnit(String u){
        status.remove(u);
    }

}
