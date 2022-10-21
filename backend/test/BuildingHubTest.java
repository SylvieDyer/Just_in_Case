import java.util.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import backend.Building;
import backend.BuildingHub;
import java.io.*;
import java.sql.*;

public class BuildingHubTest {
    Connection conn;
    @Before
    public void initDatabase() {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        try (Scanner fr = new Scanner(new 
            File("C:/Users/prart/OneDrive/Documents/2022Fall/csds393/Just_in_Case/untracked.txt"))) {
            String PASS = fr.nextLine();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void clearDatabase() {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM just_in_case.building");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBuilding() {
        BuildingHub buildingHub = new BuildingHub();
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertTrue(buildingHub.addBuilding(building)); 
    }
}

