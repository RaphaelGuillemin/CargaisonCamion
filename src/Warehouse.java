import java.util.ArrayList;

public class Warehouse {
  //contient tous les batiments donnes dans le fichier initial
  private ArrayList<Building> buildings;

  //contient les indexs des batiments visites pour le fichier final UTILISER UNE FILE SI ON PREFERE
  private ArrayList<Integer> visitedBuildings;

  public Warehouse(){
    this.buildings = new ArrayList<>();
  }

  public Integer maxBoxes(){
    //retourne l'index du batiment qui contient le plus de boites
    int max = 0;
    for (int i=1;i<this.buildings.size();i++){
      if(this.buildings.get(i).getNBoxes()>this.buildings.get(max).getNBoxes()){
        max=i;
      }
    }
    return max;
  }

  public ArrayList<Building> getAllBuildings() {
    return this.buildings;
  }

  // Retourne le batiment a l'index specifie
  public Building getBuilding(int index) {
    return this.buildings.get(index);
  }

  public ArrayList<Integer> getVisitedBuildings() {
    return visitedBuildings;
  }
}
