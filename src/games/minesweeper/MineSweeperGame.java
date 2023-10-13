package games.minesweeper;

public class MineSweeperGame implements Runnable {
    GUI gui = new GUI();
    @Override
    public void run() {

        while(true){
            gui.repaint();
            if(!gui.resetter) {
                gui.checkVictoryStatus();
//                System.out.println("Victory: " + gui.victory + ", Defeat: " + gui.defeat);
            }
        }
    }
}