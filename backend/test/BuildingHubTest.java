import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import backend.Building;
import backend.BuildingHub;

public class BuildingHubTest {
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
    }
}
