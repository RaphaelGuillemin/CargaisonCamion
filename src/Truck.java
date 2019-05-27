public class Truck {
  private int capacity;
  private int nBoxesToTransport;
  private double latitudeInit;
  private double longitudeInit;
  private int nBoxes;

  public Truck(int nBoxesToTransport, int capacity) {
    this.capacity = capacity;
    this.nBoxesToTransport = nBoxesToTransport;
  }

  public Integer minDistance(){
    //doit retourner l'index du prochain batiment le plus proche

    return 0;
  }

  public double distance(Building building){
    //retourne la distance entre le camion et le batiment
    return 0.0;
  }

  public void addBoxes(Building building){
    //ajouter boites au camion et soustraire à la capacité totale du camion
    int num = building.getNBoxes();
    if (num > capacity){
      num = capacity;
    }
    nBoxes += num;
    capacity -= num;
    //retire les boites du batiment
    building.removeBoxes(num);
  }

  public void changePos(Building building){
    //utilise la position du batiment avec le plus de boite pour définir la sienne
    this.latitudeInit = building.getLatitude();
    this.longitudeInit = building.getLongitude();
  }

  public int getCapacity(){
    return this.capacity;
  }
}
