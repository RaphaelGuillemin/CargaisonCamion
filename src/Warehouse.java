import java.util.ArrayList;
import java.util.Queue;

public class Warehouse {
  //contient tous les batiments donnes dans le fichier initial
  private ArrayList<Building> buildings;
  private Queue<Building> visitedBuildings; // Contains the buildings visited by the lifts

  public Warehouse(){
    this.buildings = new ArrayList<>();
  }

  /*public Integer maxBoxes(){
    //retourne l'index du batiment qui contient le plus de boites
    int max = 0;
    for (int i=1;i<this.buildings.size();i++){
      if(this.buildings.get(i).getNBoxes()>this.buildings.get(max).getNBoxes()){
        max=i;
      }
    }
    return max;
  } */

  // Returns the building with the most boxes
  public Building maxBoxes() {
    int index = 0;
    int max = getBuilding(index).getNBoxes();

    for (int i = 1; i < getAllBuildings().size() ; i++) {
      if (getBuilding(i).getNBoxes() > max) {
        index = i;
        max = getBuilding(index).getNBoxes();
      }
    }
    return getBuilding(index);
  }

  public ArrayList<Building> getAllBuildings() {
    return this.buildings;
  }

  // Retourne le batiment a l'index specifie
  public Building getBuilding(int index) {
    return this.buildings.get(index);
  }

  public Queue<Building> getVisitedBuildings() {
    return visitedBuildings;
  }
}
