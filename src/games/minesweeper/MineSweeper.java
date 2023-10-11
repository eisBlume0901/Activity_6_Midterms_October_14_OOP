package games.minesweeper;

public class MineSweeper implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args) {
//        new Thread(new MineSweeper()).start();
    }

    @Override
    public void run() {
        while(true){
            gui.repaint();
            if(!gui.resetter) {
                gui.checkVictoryStatus();
            }
        }
    }
}
