package games.racecar;

public class RaceCarGame implements Runnable {

    private Game game;

    @Override
    public void run() {
        game = new Game();
        Thread gameThread = new Thread(game);
        gameThread.start();
    }

    public static void main(String[] args)
    {
        RaceCarGame raceCarGame = new RaceCarGame();
        raceCarGame.run();
    }
}