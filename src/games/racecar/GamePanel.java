package games.racecar;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    private Car playerCar;
    private Car enemyCar;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;
    private int cameraX;
    private int cameraY;
    private int trackLength;
    public int countdown = 3;
    public boolean hasTied;

    public GamePanel(Car PlayerCar, Car EnemyCar, int TrackLength) {
        this.playerCar = PlayerCar;
        this.enemyCar = EnemyCar;
        this.trackLength = TrackLength * 4;

        Thread countdownThread = new Thread(() -> {
            try {
                while (countdown > -1) {
                    Thread.sleep(1000);
                    countdown--;
                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        countdownThread.start();
    }

    public void Camera() {
        cameraX = (screenWidth / 2) - playerCar.carX;
        cameraY = (screenHeight / 2) - playerCar.carY;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Camera();

        int trackX = (screenWidth - playerCar.carX) / 2;
        int trackY = (screenWidth / 2);
        int trackSize = (trackLength * 2);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(trackX + cameraX - 1000, trackY - cameraY, trackSize, 500);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(trackX + cameraX - 1000, trackY - cameraY - 50, trackSize, 100);
        g.setColor(Color.GRAY);
        g.fillRect(trackX + cameraX - 1000, trackY - cameraY + 50, trackSize, 10);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(trackX + cameraX - 1000, trackY - cameraY + 500, trackSize, 100);

        g.setColor(Color.WHITE);
        g.fillRect(trackX + cameraX - 500, trackY - cameraY + 100, 25, 350);

        g.setColor(Color.YELLOW);
        int numCuts = trackSize / 50;
        int cutWidth = trackSize / numCuts;
        for (int i = 1; i < numCuts; i += 2) {
            int cutX = trackX + i * cutWidth + cameraX;
            int cutY = trackY - cameraY / 2 - 10;
            g.fillRect(cutX, cutY, cutWidth, 15);
        }

        if (playerCar.carX >= trackLength && enemyCar.carX >= trackLength) {
            hasTied = true;
            g.setColor(Color.PINK);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("TIE :I", playerCar.carX + cameraX - 40, playerCar.carY + cameraY - 450 - screenHeight / 2);
        }

        if (playerCar.carX >= trackLength && !hasTied) {
            playerCar.hasWon = true;
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("YOU WON :D", playerCar.carX + cameraX - 40, playerCar.carY + cameraY - 450 - screenHeight / 2);
        }

        if (enemyCar.carX >= trackLength && !hasTied) {
            enemyCar.hasWon = true;
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("YOU LOST :(", playerCar.carX + cameraX - 40, playerCar.carY + cameraY - 450 - screenHeight / 2);
        }

        Font font = new Font("Arial", Font.BOLD, 75);
        g.setFont(font);

        if (countdown == 3) {
            g.setColor(Color.RED);
            g.drawString(String.valueOf(countdown), playerCar.carX + cameraX, playerCar.carY + cameraY - 450);
        } else if (countdown == 2) {
            g.setColor(Color.ORANGE);
            g.drawString(String.valueOf(countdown), playerCar.carX + cameraX, playerCar.carY + cameraY - 450);
        } else if (countdown == 1) {
            g.setColor(Color.GREEN);
            g.drawString(String.valueOf(countdown), playerCar.carX + cameraX, playerCar.carY + cameraY - 450);
        } else if (countdown == 0) {
            g.setColor(Color.GREEN);
            g.drawString("GO!", playerCar.carX + cameraX - 40, playerCar.carY + cameraY - 450);
        }

        font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);

        g.setColor(Color.BLACK);
        g.drawString("Acceleration: " + playerCar.moveSpeed, playerCar.carX + cameraX - 900 + screenWidth / 10, playerCar.carY + cameraY - 300);
        g.drawString("Current Gear: " + playerCar.currentGear, playerCar.carX + cameraX - 900 + screenWidth / 10, playerCar.carY + cameraY - 350);
        g.drawString("State: " + playerCar.state, playerCar.carX + cameraX - 900 + screenWidth / 10, playerCar.carY + cameraY - 400);
        if (playerCar.hasOverheated) {
            g.setColor(Color.PINK);
            g.drawString("PLAYER CAR OVERHEATED", playerCar.carX + cameraX - 900 + screenWidth / 10, playerCar.carY + cameraY - 250);
        }

        g.setColor(Color.RED);
        g.drawString("Acceleration: " + enemyCar.moveSpeed, playerCar.carX + cameraX + 700 - screenWidth / 7, playerCar.carY + cameraY - 300);
        g.drawString("Current Gear: " + enemyCar.currentGear, playerCar.carX + cameraX + 700 - screenWidth / 7, playerCar.carY + cameraY - 350);
        if (enemyCar.hasOverheated) {
            g.setColor(Color.PINK);
            g.drawString("ENEMY CAR OVERHEATED", playerCar.carX + cameraX + 555 - screenWidth / 7, playerCar.carY + cameraY - 250);
        }

        g.setColor(Color.BLACK);
        g.fillRect(playerCar.carX + cameraX, playerCar.carY + cameraY - 150, 250, 200);
        g.fillOval(playerCar.carX + cameraX + 180, playerCar.carY + cameraY + 20, 50, 50);
        g.fillOval(playerCar.carX + cameraX + 18, playerCar.carY + cameraY + 20, 50, 50);

        g.setColor(Color.RED);
        g.fillRect(enemyCar.carX + cameraX, enemyCar.carY + cameraY + 150, 250, 200);
        g.fillOval(enemyCar.carX + cameraX + 180, enemyCar.carY + cameraY + 320, 50, 50);
        g.fillOval(enemyCar.carX + cameraX + 18, enemyCar.carY + cameraY + 320, 50, 50);
    }
}
