import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Warehouse {

  private ArrayList<Building> buildings; // Contains all the buildings of the input file
  private Queue<Building> visitedBuildings = new LinkedList<>(); // Contains the buildings visited by the lifts

  Warehouse(){
    this.buildings = new ArrayList<>();
  }

  // Returns the building with the most boxes
  Building maxBoxes() {
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
  Building minDistance(){


    return new Building(0,0,0);
  }

  // Returns the Arraylist containing all the buildings
  ArrayList<Building> getAllBuildings() {
    return this.buildings;
  }

  // Returns the building at the index specified
  private Building getBuilding(int index) {
    return this.buildings.get(index);
  }

  // Returns the Queue of all the buildings visited
  Queue<Building> getVisitedBuildings() {
    return visitedBuildings;
  }
}
