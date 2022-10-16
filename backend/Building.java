package Just_in_Case;
import java.util.*;
import java.sql.*;

public class Building {
    private String buildingName;
    private HashMap<String,Status> status;
    private String description;
    private HashMap<String, Timestamp> timeOfLastEvent;

    public Building(String name, String description, List<String> facilities){
        this.buildingName = name;
        this.description = description;
        for(String facility : facilities){
            status.put(facility, Status.NOT_BUSY);
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
