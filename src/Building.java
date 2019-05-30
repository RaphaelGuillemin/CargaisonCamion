public class Building {
  private double latitude;
  private double longitude;
  private int nBoxes;
  private boolean visited;
  private double distance;

  Building(double latitude, double longitude, int nBoxes) {
    this.latitude = latitude;
    this.longitude = longitude;
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

  double getLatitude() {
    return this.latitude;
  }

  double getLongitude() {
    return this.longitude;
  }

  int getNBoxes() {
    return this.nBoxes;
  }

  void setDistance(double distance){
    this.distance=distance;
  }

  double getDistance(){
    return this.distance;
  }
}
