import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Testbench {

  public static void main(String[] args) throws Exception {
      String[] arguments = new String[2];
      for (int i=100;i<2505;i=i+50){
        arguments[0] = "CargaisonCamion/src/tp1Input2/"+i+".txt";
        arguments[1] = "CargaisonCamion/output/hey"+i+".txt";
        double tempsIni = System.nanoTime();
        Tp1.main(arguments);
        System.out.println("0,"+((""+((System.nanoTime() - tempsIni)/10E9)).substring(2)));
      }


  }

}
