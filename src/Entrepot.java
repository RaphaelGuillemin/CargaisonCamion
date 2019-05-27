import java.util.ArrayList;

public class Entrepot {
  //contient tous les batiments donnes dans le fichier initial
  private ArrayList<Batiment> batiments;

  //contient les indexs des batiments visites pour le fichier final UTILISER UNE FILE SI ON PREFERE
  private ArrayList<Integer> visites;

  Entrepot(){
    this.batiments = new ArrayList<>();
  }

  public Integer batimentMax(){
    //retourne l'index du batiment qui contient le plus de boites
    int max = 0;
    for (int i=1;i<this.batiments.size();i++){
      if(this.batiments.get(i).getNmbBoite()>this.batiments.get(max).getNmbBoite()){
        max=i;
      }
    }
    return max;
  }

  public ArrayList<Batiment> getAllBatiments() {
    return this.batiments;
  }

  // Retourne le batiment a l'index specifie
  public Batiment getBatiment(int index) {
    return this.batiments.get(index);
  }

  public ArrayList<Integer> getVisites() {
    return visites;
  }
}
