import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Tp1 {
  private static File inputFile;
  private static File outputFile;
  private static Scanner scanner;
  private static Truck truck;
  private static Warehouse warehouse;

  // Loads the truck for the first time at the building with the most boxes and sets the truck's position to the
  // building's position
  public static void initialLoad() {
    //remplit le camion
    Building maxBoxesBuilding = warehouse.maxBoxes();
    truck.changePos(maxBoxesBuilding);
    truck.addBoxes(maxBoxesBuilding);
    //defini le batiment comme visite
    maxBoxesBuilding.setVisited(true);
    //AJOUTER ICI L'INDEX DU BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
    warehouse.getVisitedBuildings().add(maxBoxesBuilding);
  }
  public static void main(String[] args) throws Exception{
      //entree = new File("tp1Input/camionentrepot");    EN COMMENTAIRE CAR NE FONCTIONNE PAS POUR DÉBUG
      //sortie = new File(args[1]);
      warehouse = new Warehouse();

      //creation de l'arraylist des batiments et de leur nombre de boite
      parse();

      //remplit le camion
      int indexMaxBoxes = warehouse.indexMaxBoxes();
      Building maxBoxesBuilding = warehouse.getBuilding(indexMaxBoxes);
      truck.changePos(maxBoxesBuilding);
      truck.addBoxes(maxBoxesBuilding);
      //defini le batiment comme visite
      maxBoxesBuilding.setVisited(true);
      //AJOUTER ICI L'INDEX DU BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
      warehouse.getVisitedBuildings().add(indexMaxBoxes);

  // Loads the truck after the initial load. The truck's position remains the same.
  public static void loadTruck() {
    int indexClosestBuilding = truck.minDistance();
    Building closestBuilding = warehouse.getBuilding(indexClosestBuilding);
    truck.addBoxes(closestBuilding);
    closestBuilding.setVisited(true);
    //AJOUTER ICI LE BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
    warehouse.getVisitedBuildings().add(closestBuilding);
  }



  public static void main(String[] args) throws Exception{
      //entree = new File("tp1Input/camionentrepot");    EN COMMENTAIRE CAR NE FONCTIONNE PAS POUR DÉBUG
      //sortie = new File(args[1]);
      Warehouse warehouse = new Warehouse();

      //creation de l'arraylist des batiments et de leur nombre de boite
      parser();
      initialLoad();

      //procede au reste tant que le camion n'est pas remplit
      while (truck.getCapacity() != 0){
        loadTruck();
      }

      //après ca il faudra utiliser la file pour construire le fichier de sortie


  }

  public static void parse()throws FileNotFoundException{
    //if(entree==null){
      //throw new FileNotFoundException("Fichier Introuvable");
    //}
    scanner = new Scanner(new File("C:\\Users\\Raph\\Desktop\\camionentrepot")); //trouver un moyen pour ne pas avoir a faire ca pour le debuggage

    //la premiere ligne du fichier donne la capacité du camion et le nombre de boites a transporter
    String line = scanner.nextLine();
    int nbrBoitesDemande = Integer.parseInt(line.split(" ")[0]);
    int capacite = Integer.parseInt(line.split(" ")[1]);
    if (nbrBoitesDemande<=capacite) {
      truck = new Truck(nbrBoitesDemande, capacite);
    } else {
      System.err.println("The number of boxes to be loaded into the truck exceeds its maximum capacity.");
    }

    //scanne le document au complet et remplit l'arraylist des batiments
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

  //fonction qui cree un batiment en fonction des 2 parametres donnes par le fichier
  public static void newBuilding(String[] ligne){
    String coordonnes = ligne[1].substring(1,ligne[1].length()-1);
    double posLat = Double.parseDouble(coordonnes.split(",")[0]);
    double posLong = Double.parseDouble(coordonnes.split(",")[1]);
    warehouse.getAllBuildings().add(new Building(posLat,posLong,Integer.parseInt(ligne[0])));
  }
}
