public class Truck {
  private int capacity;
  private int nBoxesToTransport;
  private Coordinates coords;
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
    if (num > this.capacity){
      num = this.capacity;
    }
    if (num > this.nBoxesToTransport - this.nBoxes) {
      num = this.nBoxesToTransport - this.nBoxes; // Number of boxes left to transport
    }
    this.nBoxes += num;
    this.capacity -= num;
    // Remove boxes from the building
    building.removeBoxes(num);
  }

  // Sets the truck's position to the building with the most boxes
  void setCoords(Building building){
    this.coords = new Coordinates(building.getCoords().getLatitude(), building.getCoords().getLongitude());
  }

  // Returns truck's capacity
  int getCapacity(){
    return this.capacity;
  }

  public Coordinates getCoords() {
    return this.coords;
  }

  public int getNBoxes() {
    return this.nBoxes;
  }

  public int getNBoxesToTransport() {
    return this.nBoxesToTransport;
  }
}
