public class Camion {
  private int capacite;
  private int nmbBoiteDemandees;
  private double positionLatInit;
  private double positionLongInit;
  private int nbrBoites;

  Camion(int capacite, int nmbBoiteDemandees, int positionLatInit,int positionLongInit){
    this.capacite=capacite;
    this.nmbBoiteDemandees=nmbBoiteDemandees;
    this.positionLatInit=positionLatInit;
    this.positionLongInit=positionLongInit;
  }

  public Batiment distanceMin(){
    //doit retourner les batiments les plus proches

    return new Batiment(0,0,0);
  }

  public double distance(Batiment batiment){
    //retourne la distance entre le camion et le batiment
    return 0.0;
  }

  public void ajouterBoites(){
    //ajouter boites au camion
  }
}
