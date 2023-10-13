package games.racecar;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RaceCarGame extends JFrame implements Runnable {

    Scanner scn = new Scanner(System.in);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;
    private boolean isAutomatic;
    private Car enemyCar;
    private Car playerCar;
    private Controller playerController;
    private GamePanel gp;
    private int trackLength;
    public int countdown = 3;

    public RaceCarGame() {

        System.out.println("AUTOMATIC or MANUAL");
        String carType = scn.nextLine();

        isAutomatic = carType.equalsIgnoreCase("automatic");

        System.out.println("Track Length (in Meters): ");
        trackLength = scn.nextInt();
        trackLength *= 4;

        enemyCar = new Car(true, true);
        playerCar = new Car(isAutomatic, false);
        playerController = new Controller(playerCar);
        gp = new GamePanel(playerCar, enemyCar, trackLength);

        setTitle("VROOM VROOM!");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        addKeyListener(playerController);
        setFocusable(true);

        add(gp);
        setVisible(true);
    }

    @Override
    public void run() {

        while(true) {

            repaint();

            if (gp.countdown <= 0 && !playerCar.hasWon && !enemyCar.hasWon && !gp.hasTied){

                playerCar.stateMachine();
                enemyCar.stateMachine();

                try { //Cap game to 60-ish FPS. THIS IS WHAT HAS BEEN CAUSING ME ISSUES
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
