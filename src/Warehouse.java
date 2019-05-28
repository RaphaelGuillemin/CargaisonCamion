import java.util.ArrayList;
import java.util.Queue;

public class Warehouse {

  private ArrayList<Building> buildings; // Contains all the buildings of the input file
  private Queue<Building> visitedBuildings; // Contains the buildings visited by the lifts

  public Warehouse(){
    this.buildings = new ArrayList<>();
  }

  // Returns the building with the most boxes
  public Building maxBoxes() {
    int index = 0;
    int max = getBuilding(index).getNBoxes();

    for (int i = 1; i < this.buildings.size() ; i++) {
      if (getBuilding(i).getNBoxes() > max) {
        index = i;
        max = getBuilding(index).getNBoxes();
      }
    }
    return getBuilding(index);
  }

  // Sorts the Array of all the buildings
  public Building minDistance(){


    return new Building(0,0,0);
  }

  // Returns the Arraylist containing all the buildings
  public ArrayList<Building> getAllBuildings() {
    return this.buildings;
  }

  // Returns the building at the index specified
  public Building getBuilding(int index) {
    return this.buildings.get(index);
  }

  // Returns the Queue of all the buildings visited
  public Queue<Building> getVisitedBuildings() {
    return visitedBuildings;
  }
}
