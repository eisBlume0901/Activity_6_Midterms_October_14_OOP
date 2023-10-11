package games.minesweeper;

public class MineSweeperGame implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args) {
        new Thread(new MineSweeperGame()).start();
    }

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