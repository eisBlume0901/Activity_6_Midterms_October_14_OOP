package games.minesweeper;

public class MineSweeperGame implements Runnable {
    private GUI gui;
    @Override
    public void run() {
        gui = new GUI();
        while(true)
        {
            gui.repaint();
            if(!gui.resetter)
            {
                gui.checkVictoryStatus();
//                System.out.println("Victory: " + gui.victory + ", Defeat: " + gui.defeat);
            }
        }
    }

    public static void main(String[] args)
    {
        MineSweeperGame mineSweeperGame = new MineSweeperGame();
        mineSweeperGame.run();
    }
}