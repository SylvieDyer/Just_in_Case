package Just_in_Case;
import java.util.*;

public class BuildingHub {
    /*
     * Note:
     * in software design doc, buildings was a hashmap in diagram but a list in class description, so i went with a list
     * feel free to change it if u desire to do it
     */

    private List<Building> buildings;
    public BuildingHub(){
        buildings = new ArrayList<Building>();
        //prarthana, pull list of buildings from db to initialize buildings
    }

    private void addBuilding(Building building){
        buildings.add(building);
    }

    private void removeBuilding(Building building){
        buildings.remove(building);
    }
    
}
