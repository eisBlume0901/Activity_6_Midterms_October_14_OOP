package games.racecar;
import games.minesweeper.MineSweeperGame;

import java.awt.*;

public class Test {

    public static void main(String[] args) {
        //Lagay mo to sa If Else mo para ma run.

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        System.out.println(screenWidth);
        System.out.println(screenHeight);

//        Game game = new Game();
//        Thread gameThread = new Thread(game);
//        gameThread.start();

        RaceCarGame raceCarGame = new RaceCarGame();
        raceCarGame.run();

//        MineSweeperGame mineSweeperGame = new MineSweeperGame();
//        mineSweeperGame.run();
    }

}
