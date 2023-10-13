package games.racecar;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        //Lagay mo to sa If Else mo para ma run.

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        System.out.println(screenWidth);
        System.out.println(screenHeight);

        RaceCarGame game = new RaceCarGame();
        Thread gameThread = new Thread(game);
        gameThread.start();
    }

}
