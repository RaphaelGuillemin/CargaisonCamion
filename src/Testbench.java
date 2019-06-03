public class Testbench {

  public static void main(String[] args) throws Exception {
      String[] arguments = new String[2];
      arguments[0] = "CargaisonCamion/src/tp1Input/camion_entrepot6.txt";
      arguments[1] = "hey.txt";
      double tempsIni=System.currentTimeMillis();
      Tp1.main(arguments);
      System.out.println(System.currentTimeMillis()-tempsIni);
  }

}
