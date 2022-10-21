import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import backend.Building;
import java.io.*;
import java.sql.*;

public class BuildingTest{
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
    public void testInitialization(){
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertEquals("Building A", building.getName());
        assertEquals("This is a building", building.getDescription());
        assertEquals("NOT_BUSY",building.getStatus("restroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("classroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("cafe").toString());
    }

    @Test
    public void testName(){
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertEquals("Building A", building.getName());
    }

    @Test
    public void testDescription(){
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertEquals("This is a building", building.getDescription());
    }

    @Test
    public void testGetStatus(){
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertEquals("NOT_BUSY",building.getStatus("restroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("classroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("cafe").toString());
    }

    @Test
    public void testAddandRemoveUnit() {
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);

        building.addBuildingUnit("classroom");
        assertEquals("NOT_BUSY", building.getStatus("classroom").toString());
        
        building.removeBuildingUnit("classroom");
        assertNull(building.getStatus("classroom"));
    }
}
