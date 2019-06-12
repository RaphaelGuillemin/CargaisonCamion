import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {

    private int nBoxesLeft; // Total number of boxes left in the warehouse that can be loaded in the truck
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

    // Returns the index of the next building that has not been visited by the lifts yet. The array must be sorted by
    // distance before calling method.
    public int findNextUnvisitedBuilding(ArrayList<Building> buildings) {
        int i = 0;
        Building unvisited = buildings.get(i);
        while (i < buildings.size() && unvisited.isVisited()) {
            unvisited = buildings.get(i);
            i++;
        }
        if (i == buildings.size()) {
            //System.err.println("All buildings have been visited. There are not enough boxes in the warehouse to load " +
                    //"the truck to desired amount.");
            return -1;
        }
        return i - 1;
    }

    // Returns the index of the closest building to the truck that has not been visited yet. If the closest distance
    // to the truck is the same for mor than one building, it returns the index of the one with the smallest latitude.
    // If the latitudes are the same, it returns the one with the smallest longitude.
    public int closestBuildingToTruck(ArrayList<Building> buildings) {
        int closestIndex = this.findNextUnvisitedBuilding(buildings);

        if (closestIndex != -1 && closestIndex != buildings.size() - 1) {
            int currentIndex = closestIndex + 1;
            Building closestBuilding = buildings.get(closestIndex);
            Building current = buildings.get(currentIndex);

            while (currentIndex < buildings.size() && (closestBuilding.getDistanceFromTruck() == current.getDistanceFromTruck())) {
                if (closestBuilding.getCoords().getLatitude() > current.getCoords().getLatitude()) {
                    closestIndex = currentIndex;

                } else if (closestBuilding.getCoords().getLongitude() > current.getCoords().getLongitude()) {
                    closestIndex = currentIndex;
                }
                currentIndex++;
            }
        }
        return closestIndex;
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

    public int getNBoxesLeft() {
        return this.nBoxesLeft;
    }

    public void setNBoxesLeft(int nBoxesLeft) {
        this.nBoxesLeft = nBoxesLeft;
    }

    public boolean areAllBuildingsVisited() {
        return this.visitedBuildings.size() == buildings.size();
    }
}
