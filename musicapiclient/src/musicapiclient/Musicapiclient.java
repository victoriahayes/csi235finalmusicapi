package musicapiclient;

public class Musicapiclient {

   //main calls guis
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_base start= new GUI_base();
            }
        });
    }

}
