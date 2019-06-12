import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Testbench {

  public static void main(String[] args) throws Exception {
      String[] arguments = new String[2];
      for (int i=100;i<2505;i=i+50){
          arguments[0] = "src/tp1Input2/" + i + ".txt";
          arguments[1] = "output/hey" + i + ".txt";
        double tempsIni = System.nanoTime();
        Tp1.main(arguments);
          double tempsFinal = System.nanoTime() - tempsIni;
          System.out.println("0," + (("" + ((tempsFinal) / 10E9)).substring(2)));
      }


  }

}
