import java.io.*;
import java.util.*;

public class Tp1 {
    private static File inputFile;
    private static Truck truck;
    private static Warehouse warehouse;


    public static void main(String[] args) throws Exception{

        inputFile = new File(args[0]);
        warehouse = new Warehouse();
        ArrayList <Building> buildings = warehouse.getAllBuildings();

        // Creates Arraylist
        parse();

        // Loads the truck with the boxes of the building with most boxes
        initialLoad();

        // Calculates the distance between the truck and each building
        distanceTruckBuildings(truck, buildings);

        // Sorts the buildings of the warehouse by their distance to the truck
        warehouse.quickSortDistance(buildings, 0, buildings.size() - 1);

        // Adds boxes from the other buildings while there are still boxes to transport, the buildings
        // of the warehouse are not empty and there are still buildings to visit
        while (truck.getNBoxes() < truck.getNBoxesToTransport() && warehouse.getNBoxesLeft() != 0 &&
                !warehouse.areAllBuildingsVisited()) {
            int closestIndex = warehouse.closestBuildingToTruck(buildings);
            if (closestIndex != -1) {
                loadTruck(buildings.get(closestIndex));
            } else {
                break;
            }
        }

        loadOutput(args[1]);
        System.out.println(buildings.size());
        System.out.println(truck.getNBoxesToTransport());
    }

    // Loads the truck for the first time at the building with the most boxes and sets the truck's position to the
    // building's position
    private static void initialLoad() {
        // Fills the Truck
        Building maxBoxesBuilding = warehouse.maxBoxes();
        truck.setCoords(maxBoxesBuilding);

        // Updates the number of boxes left in all the buildings of the warehouse
        warehouse.setNBoxesLeft(warehouse.getNBoxesLeft() - maxBoxesBuilding.getNBoxes());

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

        truck.addBoxes(closestBuilding);
        closestBuilding.setVisited(true);
        warehouse.getVisitedBuildings().add(closestBuilding);
    }

    // Calculates the distance between the truck and each building and stores it in the building's distanceFromTruck
    // attribute
    private static void distanceTruckBuildings(Truck truck, ArrayList<Building> buildings) {
        Coordinates truckPos = truck.getCoords();

        for (int i = 0; i < buildings.size(); i++) {
            Building current = buildings.get(i);
            current.setDistanceFromTruck(truckPos.distanceTo(current.getCoords()));
        }
    }

    private static void parse()throws FileNotFoundException{
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

        // Reads file and fills Arraylist with the buildings
        while (scanner.hasNext()){
            String[] line = scanner.nextLine().split(" ");
            if (line.length == 2) {
                newBuilding(line);
            } else {
                String[] firstBuilding = Arrays.copyOfRange(line, 0, 2);
                String[] secondBuilding = Arrays.copyOfRange(line, 2, 4);
                newBuilding(firstBuilding);
                newBuilding(secondBuilding);
            }
        }
    }

    // Creates a building with the 2 entries from the input file
    private static void newBuilding(String[] line) {
        String coords = line[1].substring(1, line[1].length() - 1);
        double latitude = Double.parseDouble(coords.split(",")[0]);
        double longitude = Double.parseDouble(coords.split(",")[1]);
        int nBoxes = Integer.parseInt(line[0]);
        warehouse.getAllBuildings().add(new Building(latitude, longitude, nBoxes));
        warehouse.setNBoxesLeft(warehouse.getNBoxesLeft() + nBoxes);
    }

    private static void loadOutput(String string){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(string));
            writer.write("Truck Position: (" + truck.getCoords().getLatitude() + "," +
                    truck.getCoords().getLongitude() + ")");
            while(!warehouse.getVisitedBuildings().isEmpty()){
                writer.newLine();
                Building building = warehouse.getVisitedBuildings().remove();
                writer.write("Distance:" + Math.round(building.getDistanceFromTruck()*10)/10.0 + "\t" + "Number of boxes:"
                        + building.getNBoxes() + "\t" + "Position:(" + building.getCoords().getLatitude() + ","
                        + building.getCoords().getLongitude() + ")");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
