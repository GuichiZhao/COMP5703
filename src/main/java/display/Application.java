package display;

import model.Implicant;

import javax.swing.*;

/**
 * Created by guichi on 28/03/2015.
 */
public class Application {
    public static void main(String[] args) {
        //  JFrame application=new JFrame();

        //  application.add(new GraphPanel());

        JFrame application = new GraphPanel();

        application.pack();

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      //  application.setSize(800, 500);
        application.setVisible(true);


    }
}
