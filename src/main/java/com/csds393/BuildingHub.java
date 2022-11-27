package com.csds393;
import java.util.*;
import java.sql.*;
import java.io.*;

public class BuildingHub {
    /*
     * Note:
     * in software design doc, buildings was a hashmap in diagram but a list in class description, so i went with a list
     * feel free to change it if u desire to do it
     */

    private List<Building> buildings;
    private Connection conn;

    public BuildingHub(){
        buildings = new ArrayList<Building>();

        // try {
        //     openConnection();
        // } catch (FileNotFoundException e1) {
        //     e1.printStackTrace();
        // }
    }

    public boolean addBuilding(Building building){
        return buildings.add(building);
    }

    public void removeBuilding(Building building){
        buildings.remove(building);

        // try(Statement stmt = conn.createStatement()) {
        //     stmt.executeUpdate(
        //         "DELETE FROM just_in_case.building WHERE just_in_case.building.buildingName = '" + building.getName() + "'"); 
        // } catch(SQLException e) {
        //     e.printStackTrace();
        // }
    }

    public void removeBuildingByName(String buildingName){
        boolean removed = false;
        for(int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getBuildingName().equals(buildingName)) {
                buildings.remove(i);
                i--;
                removed = true;
            }
        }
        
        if (removed == false) {
            throw new IllegalArgumentException("Tried to remove building that does not exist");
        }

        // try(Statement stmt = conn.createStatement()) {
        //     stmt.executeUpdate(
        //         "DELETE FROM just_in_case.building WHERE just_in_case.building.buildingName = '" + buildingName + "'"); 
        // } catch(SQLException e) {
        //     e.printStackTrace();
        // }
    }

    public List<Building> getAllBuildings() {
        return buildings;
    }

    //Returns null if no such building.
    public Building getBuildingByName(String name) {
        for (Building b : buildings) {
            if (b.getBuildingName().equals(name)) {
                return b;
            }
        }
        return null;
    }
}
