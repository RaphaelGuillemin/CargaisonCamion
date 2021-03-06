import java.io.*;
import java.util.*;

// Tp1 manages the warehouse cargo truck process
public class Tp1 {
    private static File inputFile;
    private static Truck truck;
    private static Warehouse warehouse;

    public static void main(String[] args) throws Exception{

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        inputFile = new File(inputFilePath);
        warehouse = new Warehouse();
        ArrayList <Building> buildings = warehouse.getAllBuildings();

        // Parses the input file
        parse(inputFile);

        // Loads the truck at the building with the most boxes
        initialLoad(truck, warehouse);

        // Calculates the distance between the truck and each building
        distanceTruckBuildings(truck, buildings);

        // Sorts the buildings of the warehouse by their distance to the truck
        warehouse.quickSortDistance(buildings, 0, buildings.size() - 1);

        // Adds boxes from the other buildings while : there are still boxes to transport, the buildings
        // of the warehouse are not empty and there are still buildings to visit
        while (truck.getNBoxes() < truck.getNBoxesToTransport() && warehouse.getNBoxesLeft() != 0 &&
                !warehouse.areAllBuildingsVisited()) { // Approx n calls
            int closestIndex = warehouse.closestBuildingToTruck(buildings);
            if (closestIndex != -1) {
                loadTruck(buildings.get(closestIndex));
            } else {
                break;
            }
        }

        // Creates the output file
        loadOutput(outputFilePath);
    }

    // Loads the truck for the first time at the building with the most boxes and sets the truck's position to the
    // building's position
    private static void initialLoad(Truck truck, Warehouse warehouse) {
        Building maxBoxesBuilding = warehouse.maxBoxes();
        truck.setCoords(maxBoxesBuilding);

        // Updates the number of boxes left in all the buildings of the warehouse
        warehouse.setNBoxesLeft(warehouse.getNBoxesLeft() - maxBoxesBuilding.getNBoxes());

        // Fills the truck with the boxes
        truck.addBoxes(maxBoxesBuilding);

        // Sets building as visited
        maxBoxesBuilding.setVisited(true);

        // Adds building to the visited buildings queue
        warehouse.getVisitedBuildings().add(maxBoxesBuilding);
    }

    // Loads the truck after the initial load. The truck's position remains the same.
    private static void loadTruck(Building closestBuilding) {
        // Updates the number of boxes left in all the buildings of the warehouse
        warehouse.setNBoxesLeft(warehouse.getNBoxesLeft() - closestBuilding.getNBoxes());

        // Fills the truck with the boxes
        truck.addBoxes(closestBuilding);
        closestBuilding.setVisited(true);
        warehouse.getVisitedBuildings().add(closestBuilding);
    }

    // Calculates the distance between the truck and each building and stores it in the building's distanceFromTruck
    // attribute
    private static void distanceTruckBuildings(Truck truck, ArrayList<Building> buildings) { //O(n)
        Coordinates truckPos = truck.getCoords();

        for (int i = 0; i < buildings.size(); i++) { //3n
            Building current = buildings.get(i);
            current.setDistanceFromTruck(truckPos.distanceTo(current.getCoords()));
        }
    }

    // Parses the input file
    private static void parse(File inputFile) throws FileNotFoundException {
        if(inputFile == null){
            throw new FileNotFoundException("Input file not found");
        }
        Scanner scanner = new Scanner(inputFile);

        // Creates new Truck with number of boxes and capacity
        String firstLine = scanner.nextLine();
        int nBoxesToTransport = Integer.parseInt(firstLine.split(" ")[0]);
        int capacity = Integer.parseInt(firstLine.split(" ")[1]);
        if (nBoxesToTransport <= capacity) {
            truck = new Truck(nBoxesToTransport, capacity);
        } else {
            System.err.println("The number of boxes to be loaded into the truck exceeds its maximum capacity.");
        }

        // Parses the buildings data from the file
        while (scanner.hasNext()) { //6n
            String[] line = scanner.nextLine().split(" "); //n
            if (line.length == 2) { //n
                newBuilding(line);
            } else {
                String[] firstBuilding = Arrays.copyOfRange(line, 0, 2); //n
                String[] secondBuilding = Arrays.copyOfRange(line, 2, 4); //n
                newBuilding(firstBuilding); //n
                newBuilding(secondBuilding); //n
            }
        }
    }

    // Creates a building with their position and their number of boxes according to the file
    private static void newBuilding(String[] line) { //Constant
        String coords = line[1].substring(1, line[1].length() - 1);
        double latitude = Double.parseDouble(coords.split(",")[0]);
        double longitude = Double.parseDouble(coords.split(",")[1]);
        int nBoxes = Integer.parseInt(line[0]);
        warehouse.getAllBuildings().add(new Building(latitude, longitude, nBoxes));
        warehouse.setNBoxesLeft(warehouse.getNBoxesLeft() + nBoxes);
    }

    // Outputs the results in a file
    private static void loadOutput(String outputFilePath) { //Approx 3n
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write("Truck Position: (" + truck.getCoords().getLatitude() + "," +
                    truck.getCoords().getLongitude() + ")");
            while (!warehouse.getVisitedBuildings().isEmpty()) { //3n
                writer.newLine(); //n
                Building building = warehouse.getVisitedBuildings().remove(); //n
                writer.write("Distance:" + Math.round(building.getDistanceFromTruck()*10)/10.0 + "\t" + "Number of boxes:"
                        + building.getNBoxes() + "\t" + "Position:(" + building.getCoords().getLatitude() + ","
                        + building.getCoords().getLongitude() + ")"); //n
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
