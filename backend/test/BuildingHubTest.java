import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
public class BuildingHubTest {
    @Test
    public void testAddBuilding() {
        Just_in_Case.BuildingHub buildingHub = new Just_in_Case.BuildingHub();
        List<String> facilities = new ArrayList<String>();
        facilities.add("restroom");
        facilities.add("classroom");
        facilities.add("cafe");
        String name = "Building A";
        String description = "This is a building";
        Just_in_Case.Building building = new Just_in_Case.Building(name, description, facilities);
    }
}
