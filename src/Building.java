public class Building {
  private double latitude;
  private double longitude;
  private int nBoxes;
  private boolean visited;

  public Building(double latitude, double longitude, int nBoxes) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.nBoxes = nBoxes;
    this.visited = false;
  }

  public void removeBoxes(int num){
    //retirer nombre de boites mises dans le camion
    this.nBoxes = this.nBoxes - num;
  }

  public void setVisited(boolean visited){
    this.visited = visited;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public int getNBoxes() {
    return this.nBoxes;
  }

  public boolean getVisited(){
    return this.visited;
  }
}
