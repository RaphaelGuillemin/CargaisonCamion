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

  // Returns the distance between the truck and the building specified
  public double distance(Building building){
    return 0.0;
  }


  // Adds boxes to the truck and removes them from the building specified
  void addBoxes(Building building){
    int num = building.getNBoxes();
    if (num > capacity){
      num = capacity;
    }
    nBoxes += num;
    capacity -= num;
    // Remove boxes from the building
    building.removeBoxes(num);
  }

  // Changes truck's position to the building with the most boxes
  void changePos(Building building){
    this.latitudeInit = building.getLatitude();
    this.longitudeInit = building.getLongitude();
  }

  // Returns truck's capacity
  int getCapacity(){
    return this.capacity;
  }

  double getLatitudeInit() {
    return latitudeInit;
  }

  double getLongitudeInit() {
    return longitudeInit;
  }
}
