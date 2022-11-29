import java.util.*;
import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;
import com.csds393.Building;

public class BuildingTest {
    @Test
    public void testBuilding_noParam(){
        Building building = new Building();
        assertNull(building.getBuildingName());
        assertNull(building.getDescription());
        assertEquals(0,building.getBuildingID());
    }

    @Test
    public void testBuilding_twoParam(){
        Building building = new Building("Building A","This is a building");
        assertEquals("Building A",building.getBuildingName());
        assertEquals("This is a building",building.getDescription());
        assertEquals(0,building.getBuildingID());
    }

    @Test
    public void testBuilding_threeParam(){
        Building building = new Building(100,"Building A","This is a building");
        assertEquals("Building A",building.getBuildingName());
        assertEquals("This is a building",building.getDescription());
        assertEquals(100,building.getBuildingID());
    }

    @Test
    public void testSetBuildingID(){
        Building building = new Building();
        building.setBuildingID(100);
        assertEquals(100,building.getBuildingID());
    }

    @Test
    public void testSetBuildingName(){
        Building building = new Building();
        building.setBuildingName("Building A");
        assertEquals("Building A",building.getBuildingName());
    }

    @Test
    public void testSetBuildingDescription(){
        Building building = new Building();
        building.setDescription("This is a building");
        assertEquals("This is a building",building.getDescription());
    }

    @Test
    public void testGetBuildingId(){
        Building building = new Building(100,"Building A","This is a building");
        assertEquals(100,building.getBuildingID());
    }

    @Test
    public void testGetBuildingName(){
        Building building = new Building(100,"Building A","This is a building");
        assertEquals("Building A",building.getBuildingName());
    }

    @Test
    public void testGetBuildingDescription(){
        Building building = new Building(100,"Building A","This is a building");
        assertEquals("This is a building",building.getDescription());
    }

    @Test
    public void testToString(){
        Building building = new Building(100,"Building A","This is a building");
        assertEquals("name: Building Adescription: This is a building", building.toString());
    }
}

