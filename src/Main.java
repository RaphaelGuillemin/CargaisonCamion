import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  private static File entree;
  private static File sortie;
  private static Scanner scanner;
  private static Camion camion;
  private static Entrepots entrepots;

  public static void main(String[] args)throws Exception{
      //entree = new File("tp1Input/camionentrepot");    EN COMMENTAIRE CAR NE FONCTIONNE PAS POUR DÉBUG
      //sortie = new File(args[1]);
      entrepots = new Entrepots();

      //creation de l'arraylist des batiments et de leur nombre de boite
      parser();

      //remplit le camion
      int indexBatimentMax = entrepots.batimentMax();
      Batiment batimentMax = entrepots.getBatiments().get(indexBatimentMax);
      camion.changerPos(batimentMax);
      camion.ajouterBoites(batimentMax);
      //defini le batiment comme visite
      batimentMax.setVisite(true);
      //AJOUTER ICI L'INDEX DU BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
      entrepots.getVisites().add(indexBatimentMax);

      //procede au reste tant que le camion n'est pas remplit
      while (camion.getCapacite()!=0){
        int indexBatimentProche = camion.distanceMin();
        Batiment batimentProche = entrepots.getBatiments().get(indexBatimentProche);
        camion.changerPos(batimentProche);
        camion.ajouterBoites(batimentProche);
        batimentProche.setVisite(true);
        //AJOUTER ICI LE BATIMENT A LA FILE (ARRAYLIST EN CE MOMENT)
        entrepots.getVisites().add(indexBatimentProche);
      }

      //après ca il faudra utiliser la file pour construire le fichier de sortie


  }

  public static void parser()throws FileNotFoundException{
    //if(entree==null){
      //throw new FileNotFoundException("Fichier Introuvable");
    //}
    Scanner scanner = new Scanner(new File("C:\\Users\\Raph\\Desktop\\camionentrepot")); //trouver un moyen pour ne pas avoir a faire ca pour le debuggage

    //la premiere ligne du fichier donne la capacité du camion et le nombre de boites a transporter
    String line = scanner.nextLine();
    int nbrBoitesDemande = Integer.parseInt(line.split(" ")[0]);
    int capacite = Integer.parseInt(line.split(" ")[1]);
    if (nbrBoitesDemande<=capacite) {
      camion = new Camion(nbrBoitesDemande, capacite);
    } else {
      System.err.println("le nombre de boite demandé est plus grand que la capacité du camion");
    }

    //scanne le document au complet et remplit l'arraylist des batiments
    while (scanner.hasNext()){
      String[] ligne = scanner.nextLine().split(" ");
      if (ligne.length==2){
        nouveauBatiment(ligne);
      } else {
        String[] premier = Arrays.copyOfRange(ligne,0,2);
        String[] deuxieme = Arrays.copyOfRange(ligne,2,4);
        nouveauBatiment(premier);
        nouveauBatiment(deuxieme);
      }
    }

  }

  //fonction qui cree un batiment en fonction des 2 parametres donnes par le fichier
  public static void nouveauBatiment(String[] ligne){
    String coordonnes = ligne[1].substring(1,ligne[1].length()-1);
    double posLat = Double.parseDouble(coordonnes.split(",")[0]);
    double posLong = Double.parseDouble(coordonnes.split(",")[1]);
    entrepots.getBatiments().add(new Batiment(posLat,posLong,Integer.parseInt(ligne[0])));
  }
}
