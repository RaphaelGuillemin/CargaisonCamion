import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Tp1 {
  private static File inputFile;
  private static Truck truck;
  private static Warehouse warehouse;


  public static void main(String[] args) throws Exception{
      inputFile = new File(args[0]);
      warehouse = new Warehouse();

      // Creates Arraylist
      parse();

      // Adds boxes from the first building
      initialLoad();
      // Adds boxes from the other buildings as long as the Truck isn't full
      while (truck.getCapacity() != 0){
        loadTruck();
      }

      loadOutput(args[1]);
  }

  // Loads the truck for the first time at the building with the most boxes and sets the truck's position to the
  // building's position
  private static void initialLoad() {
    // Fills the Truck
    Building maxBoxesBuilding = warehouse.maxBoxes();
    truck.changePos(maxBoxesBuilding);
    truck.addBoxes(maxBoxesBuilding);
    // Sets building as visited
    maxBoxesBuilding.setVisited(true);
    // Adds building to the visited building queue
    warehouse.getVisitedBuildings().add(maxBoxesBuilding);
  }

  // Loads the truck after the initial load. The truck's position remains the same.
  private static void loadTruck() {
    Building closestBuilding = warehouse.minDistance();
    truck.addBoxes(closestBuilding);
    closestBuilding.setVisited(true);
    closestBuilding.setDistance(Math.random());
    //AJOUTER ICI LE BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
    warehouse.getVisitedBuildings().add(closestBuilding);
  }

  private static void parse()throws FileNotFoundException{
    if(inputFile==null){
      throw new FileNotFoundException("Input file not found");
    }
    Scanner scanner = new Scanner(inputFile);

    // Creates new Truck with number of boxes and capacity
    String line = scanner.nextLine();
    int nbrBoitesDemande = Integer.parseInt(line.split(" ")[0]);
    int capacite = Integer.parseInt(line.split(" ")[1]);
    if (nbrBoitesDemande<=capacite) {
      truck = new Truck(nbrBoitesDemande, capacite);
    } else {
      System.err.println("The number of boxes to be loaded into the truck exceeds its maximum capacity.");
    }

    // Reads file and fills Arraylist with the buildings
    while (scanner.hasNext()){
      String[] ligne = scanner.nextLine().split(" ");
      if (ligne.length ==2){
        newBuilding(ligne);
      } else {
        String[] premier = Arrays.copyOfRange(ligne,0,2);
        String[] deuxieme = Arrays.copyOfRange(ligne,2,4);
        newBuilding(premier);
        newBuilding(deuxieme);
      }
    }
  }

  // Creates a building with the 2 entrees from the input file
  private static void newBuilding(String[] ligne){
    String coordonnes = ligne[1].substring(1,ligne[1].length()-1);
    double posLat = Double.parseDouble(coordonnes.split(",")[0]);
    double posLong = Double.parseDouble(coordonnes.split(",")[1]);
    warehouse.getAllBuildings().add(new Building(posLat,posLong,Integer.parseInt(ligne[0])));
  }

  private static void loadOutput(String string){
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(string));
      writer.write("Truck Position:(" + truck.getLatitudeInit() + "," + truck.getLongitudeInit() + ")");
      while(!warehouse.getVisitedBuildings().isEmpty()){
        writer.newLine();
        Building building = warehouse.getVisitedBuildings().remove();
        writer.write("Distance:"+building.getDistance()+" Number of boxes:"+building.getNBoxes()+" Position:("+building.getLatitude()+","+building.getLongitude()+")");
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
