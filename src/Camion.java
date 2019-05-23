public class Camion {
  private int capacite;
  private int nmbBoiteDemandees;
  private double positionLatInit;
  private double positionLongInit;
  private int nbrBoites;

  Camion(int nmbBoiteDemandees, int capacite){
    this.capacite=capacite;
    this.nmbBoiteDemandees=nmbBoiteDemandees;
  }

  public Integer distanceMin(){
    //doit retourner l'index du prochain batiment le plus proche

    return 0;
  }

  public double distance(Batiment batiment){
    //retourne la distance entre le camion et le batiment
    return 0.0;
  }

  public void ajouterBoites(Batiment batiment){
    //ajouter boites au camion et soustraire à la capacité totale du camion
    int nbre = batiment.getNmbBoite();
    if (nbre>capacite){
      nbre=capacite;
    }
    nbrBoites+=nbre;
    capacite-=nbre;
    //retire les boites du batiment
    batiment.retirerBoites(nbre);
  }

  public void changerPos(Batiment batiment){
    //utilise la position du batiment avec le plus de boite pour définir la sienne
    this.positionLatInit = batiment.getPositionLat();
    this.positionLongInit = batiment.getPositionLong();
  }

  public int getCapacite(){
    return this.capacite;
  }
}
