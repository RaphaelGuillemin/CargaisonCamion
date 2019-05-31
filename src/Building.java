public class Building {
  private Coordinates coords;
  private int nBoxes;
  private boolean visited;
  private double distanceFromTruck;

  Building(double latitude, double longitude, int nBoxes) {
    this.coords = new Coordinates(latitude, longitude);
    this.nBoxes = nBoxes;
    this.visited = false;
  }

  // Removes boxes from the building
  void removeBoxes(int num){
    this.nBoxes = this.nBoxes - num;
  }

  void setVisited(boolean visited){
    this.visited = visited;
  }

  int getNBoxes() {
    return this.nBoxes;
  }

  void setDistanceFromTruck(double distance){
    this.distanceFromTruck = distance;
  }

  double getDistanceFromTruck(){
    return this.distanceFromTruck;
  }

  public Coordinates getCoords() {
    return this.coords;
  }
}
