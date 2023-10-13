package games.racecar;

public class RaceCarGame implements Runnable {

    Game game = new Game();

    @Override
    public void run() {
        Thread gameThread = new Thread(game);
        gameThread.start();
    }
}