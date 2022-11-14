import java.util.*;
import java.io.*;
import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

import com.csds393.Building;
import com.csds393.BuildingHub;


public class BuildingHubTest {
    Connection conn;
    @Before
    public void initDatabase() {
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        try (Scanner fr = new Scanner(new 
            File("./untracked.txt"))) {
            String PASS = fr.nextLine();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
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

    
}

