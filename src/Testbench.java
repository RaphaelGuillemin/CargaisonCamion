
public class Testbench {

  public static void main(String[] args) throws Exception {
    String[] arguments = new String[2];
    for (int i = 100; i <= 2500; i = i + 50) {
      for (int j = 0; j <= 100; j++) {
        arguments[0] = "CargaisonCamion/src/tp1Input2/" + i + ".txt";
        arguments[1] = "CargaisonCamion/output/hey" + i + ".txt";
        double tempsIni = System.nanoTime();
        Tp1.main(arguments);
        double tempsFinal = (System.nanoTime() - tempsIni) / 10E9;
        String[] num = ("" + tempsFinal).split("[.]");
        System.out.println(num[0] + "," + num[1]);
      }
    }

  }

}
