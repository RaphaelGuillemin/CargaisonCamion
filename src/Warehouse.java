import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {

    private Truck truck; // Reference to a truck in the warehouse
    private ArrayList <Building> buildings; // Contains all the buildings of the input file
    private Queue <Building> visitedBuildings = new LinkedList<>(); // Contains the buildings visited by the lifts     // distance to a specific truck.
                                                                              // Implements a Binary Search Tree.
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
    public void quickSortDistance(ArrayList <Building> buildings, int lowIndex, int highIndex) {
        if (buildings != null || buildings.size() != 0) {
            int i = lowIndex;
            int j = highIndex;
            int pivot = lowIndex + (highIndex - lowIndex) / 2;
            double pivotDistance = buildings.get(pivot).getDistanceFromTruck();

            while (i <= j) {
                while (buildings.get(i).getDistanceFromTruck() < pivotDistance) {
                    i++;
                }
                while (buildings.get(j).getDistanceFromTruck() > pivotDistance) {
                    j--;
                }
                if(i <= j) {
                    // swap
                    Building temp = buildings.get(i);
                    buildings.set(i, buildings.get(j));
                    buildings.set(j, temp);
                    i++;
                    j--;
                }
            }
            if(lowIndex < j) quickSortDistance(buildings, lowIndex, j);
            if(i < highIndex) quickSortDistance(buildings, i, highIndex);
        }
    }

    public Building findNextUnvisitedBuilding(ArrayList <Building> buildings) {
        int i = 0;
        Building unvisited = buildings.get(i);
        while (unvisited.isVisited()) {
            i++;
            unvisited =  buildings.get(i);
        }
        return unvisited;
    }

    public Building closestBuildingToTruck(Truck truck, ArrayList <Building> buildings) {
        Building closest = this.findNextUnvisitedBuilding(buildings);
        int i = buildings.indexOf(closest) + 1;
        Building current = buildings.get(i);

        while (closest.getDistanceFromTruck() == current.getDistanceFromTruck()) {
            if (closest.getCoords().getLatitude() > current.getCoords().getLatitude()) {
                closest = current;
            } else if (closest.getCoords().getLongitude() > current.getCoords().getLongitude()) {
                closest = current;
            }
            i++;
        }
        return closest;
    }

    // Returns the ArrayList containing all the buildings
    public ArrayList<Building> getAllBuildings() {
        return this.buildings;
    }

    // Returns the building at the index specified
    private Building getBuilding(int index) {
        return this.buildings.get(index);
    }

    // Returns the Queue of all the buildings visited
    public Queue<Building> getVisitedBuildings() {
        return visitedBuildings;
    }
}
