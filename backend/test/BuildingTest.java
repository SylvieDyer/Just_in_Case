import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import backend.Building;

public class BuildingTest{
    @Test
    public void testInitialization(){
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        assertEquals("This is a building", building.getDescription());
        assertEquals("NOT_BUSY",building.getStatus("restroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("classroom").toString());
        assertEquals("NOT_BUSY",building.getStatus("cafe").toString());
    }

    @Test
    public void testAddandRemove() {
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        String name = "Building A";
        String description = "This is a building";
        Building building = new Building(name, description, facilities);
        building.addBuildingUnit("cafe");
        building.removeBuildingUnit("classroom");
        assertEquals("NOT_BUSY", building.getStatus("cafe").toString());
        assertNull(building.getStatus("classroom"));
    }
}
