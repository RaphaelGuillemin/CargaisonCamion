public class Batiment {
  private double positionLat;
  private double positionLong;
  private int nmbBoite;
  private boolean visite;

  Batiment(double positionLat, double positionLong, int nmbBoite){
    this.positionLat=positionLat;
    this.positionLong=positionLong;
    this.nmbBoite=nmbBoite;
    this.visite = false;
  }

  public void retirerBoites(int nbre){
    //retirer nombre de boites mises dans le camion
    this.nmbBoite=this.nmbBoite-nbre;
  }

  public void setVisite(boolean bool){
    this.visite=bool;
  }

  public double getPositionLat() {
    return positionLat;
  }

  public double getPositionLong() {
    return positionLong;
  }

  public int getNmbBoite() {
    return nmbBoite;
  }
}
