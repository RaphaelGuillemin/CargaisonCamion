import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  private static File entree;
  private static File sortie;
  private Scanner scanner;

  public static void main(String[] args){
      entree = new File(args[0]);
      sortie = new File(args[1]);
  }

  public void parser(){
    try {
      scanner = new Scanner(entree);
    } catch (FileNotFoundException e){
      System.err.println("Le fichier d'entrée est introuvable");
    }
  }
}
