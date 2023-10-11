package games.racecar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class RaceCar extends JPanel implements KeyListener, Runnable {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int CAR_WIDTH = 40;
    private static final int ROAD_WIDTH = 600;
    private static final int LANE_WIDTH = 30;

    private int windowHeight;
    private int roadHeight;
    private int playerCarX = WINDOW_WIDTH / 2 - CAR_WIDTH / 2 - 150;
    private int playerCarY;

    private int enemyCarX = WINDOW_WIDTH / 2 - CAR_WIDTH / 2 + 150;
    private int enemyCarY;

    private boolean isMovingUp = false;
    private boolean playerWon = false;
    private boolean enemyWon = false;
    private int countdown = 3;
    private boolean isAutomatic;
    private boolean brakes = false;
    private int currentGear = 1;
    private int enemyCurrentGear = 1;
    private int moveSpeed = 0;
    private int maxSpeed = 0;
    private int initialMoveSpeed = 0;
    private int releaseSpeedDecrease = 1;
    private int enemyMoveSpeed = 0;
    private int enemyMaxSpeed = 0;
    private boolean playerOverheat = false;
    private boolean enemyOverheat = false;
    private boolean gameOver = false;

    public RaceCar(int trackHeight, boolean isAutomatic) {
        this.isAutomatic = isAutomatic;
        windowHeight = trackHeight * 10;
        roadHeight = windowHeight - LANE_WIDTH * 2;
        playerCarY = windowHeight - CAR_WIDTH - 10;
        enemyCarY = windowHeight - CAR_WIDTH - 10;

        JFrame frame = new JFrame("Car Race Game");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Thread countdownThread = new Thread(() -> {
            try {
                while (countdown > 0) {
                    Thread.sleep(1000);
                    countdown--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        countdownThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cameraOffsetX = WINDOW_WIDTH - playerCarX - CAR_WIDTH / 2;
        int cameraOffsetY = WINDOW_HEIGHT - playerCarY - CAR_WIDTH / 2;

        g.setColor(Color.GRAY);
        g.fillRect(WINDOW_WIDTH / 2 - ROAD_WIDTH / 2 + cameraOffsetX, 0 + cameraOffsetY, ROAD_WIDTH, roadHeight);

        g.setColor(Color.WHITE);
        int laneMarkingWidth = 10;
        int laneGapHeight = 20;
        int totalLaneHeight = laneMarkingWidth + laneGapHeight;
        int numLaneSegments = roadHeight / totalLaneHeight;
        for (int i = 0; i < numLaneSegments; i++) {
            int laneY = i * totalLaneHeight + cameraOffsetY;
            g.fillRect(WINDOW_WIDTH / 2 - laneMarkingWidth / 2 + cameraOffsetX, laneY, laneMarkingWidth, laneGapHeight);
        }

        //DRAW PLAYER
        g.setColor(Color.RED);
        g.fillRect(playerCarX + cameraOffsetX, playerCarY + cameraOffsetY, CAR_WIDTH, CAR_WIDTH);

        //DRAW BOT??
        g.setColor(Color.BLACK);
        g.fillRect(enemyCarX + cameraOffsetX, enemyCarY + cameraOffsetY, CAR_WIDTH, CAR_WIDTH);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Gear: " + currentGear, 500, WINDOW_HEIGHT - 20);
        g.drawString("Speed: " + moveSpeed, 500, WINDOW_HEIGHT - 40);
        g.drawString("Gear: " + enemyCurrentGear, 1350, WINDOW_HEIGHT - 20);
        g.drawString("Speed: " + enemyMoveSpeed, 1350, WINDOW_HEIGHT - 40);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER!", WINDOW_WIDTH / 2 + 420, WINDOW_HEIGHT / 2);
        }

        if (playerOverheat) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.drawString("YOUR CAR OVERHEATED", WINDOW_WIDTH / 2 + 10, WINDOW_HEIGHT / 2 + 60);
        }

        if (enemyOverheat) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.drawString("ENEMY CAR OVERHEATED", WINDOW_WIDTH / 2 + 720, WINDOW_HEIGHT / 2 + 60);
        }

        if (countdown > 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString(Integer.toString(countdown), 935 , WINDOW_HEIGHT - 500);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!playerOverheat) {
            if (isAutomatic) {
                if (key == KeyEvent.VK_W && playerCarY > 0) {
                    isMovingUp = true;
                } else if (key == KeyEvent.VK_SPACE) {
                    brakes = true;
                }
            } else {
                if (countdown <= 0) {
                    if (key == KeyEvent.VK_W && playerCarY > 0) {
                        isMovingUp = true;
                    } else if (key == KeyEvent.VK_SPACE) {
                        brakes = true;
                    } else if (key == KeyEvent.VK_SHIFT) {
                        currentGear = Math.min(currentGear + 1, 6);
                    } else if (key == KeyEvent.VK_CONTROL) {
                        currentGear = Math.max(currentGear - 1, 0);
                    }
                }
            }
        } else {
            isMovingUp = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            isMovingUp = false;
        } else if (key == KeyEvent.VK_SPACE) {
            brakes = false;
        }
    }

    private void decreaseMoveSpeed() {
        if (moveSpeed > initialMoveSpeed) {
            moveSpeed = Math.max(moveSpeed - releaseSpeedDecrease, initialMoveSpeed);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        while (!playerWon && !enemyWon && !gameOver) {

            if (enemyMoveSpeed == 120) {
                if (randomChance20()) {
                    enemyOverheat = true;
                }
            }

            if (moveSpeed == 120) {
                if (randomChance20()) {
                    playerOverheat = true;
                }
            }

            if (countdown <= 0) {

                if (!enemyOverheat) {
                    enemyCarY -= enemyMoveSpeed;
                    enemyMoveSpeed = Math.min(enemyMoveSpeed + 1, enemyMaxSpeed);

                    switch (enemyMoveSpeed) {
                        case 0:
                            enemyMaxSpeed = 20;
                            enemyCurrentGear = 1;
                            break;
                        case 20:
                            enemyMaxSpeed = 40;
                            enemyCurrentGear = 2;
                            break;
                        case 40:
                            enemyMaxSpeed = 60;
                            enemyCurrentGear = 3;
                            break;
                        case 60:
                            enemyMaxSpeed = 80;
                            enemyCurrentGear = 4;
                            break;
                        case 80:
                            enemyMaxSpeed = 100;
                            enemyCurrentGear = 5;
                            break;
                        case 100:
                            enemyMaxSpeed = 120;
                            enemyCurrentGear = 6;
                            break;
                    }
                } else {
                    if (enemyMoveSpeed > 0) {
                        enemyMoveSpeed = Math.max(enemyMoveSpeed - releaseSpeedDecrease, 0);
                    }
                }

                if (!playerOverheat) {
                    if (isAutomatic) {

                        switch (moveSpeed) {
                            case 0:
                                maxSpeed = 20;
                                currentGear = 1;
                                break;
                            case 20:
                                maxSpeed = 40;
                                currentGear = 2;
                                break;
                            case 40:
                                maxSpeed = 60;
                                currentGear = 3;
                                break;
                            case 60:
                                maxSpeed = 80;
                                currentGear = 4;
                                break;
                            case 80:
                                maxSpeed = 100;
                                currentGear = 5;
                                break;
                            case 100:
                                maxSpeed = 120;
                                currentGear = 6;
                                break;
                        }

                    } else {

                        switch (currentGear) {
                            case 0:
                                maxSpeed = 0;
                                break;
                            case 1:
                                maxSpeed = 20;
                                break;
                            case 2:
                                maxSpeed = 40;
                                break;
                            case 3:
                                maxSpeed = 60;
                                break;
                            case 4:
                                maxSpeed = 80;
                                break;
                            case 5:
                                maxSpeed = 100;
                                break;
                            case 6:
                                maxSpeed = 120;
                                break;
                        }
                    }
                } else {
                    decreaseMoveSpeed();
                }

                if (!brakes) {
                        playerCarY -= moveSpeed;
                        moveSpeed = Math.min(moveSpeed + 1, maxSpeed);
                    if (isMovingUp && playerCarY > 0) {

                    } else {
//                        playerCarY -= moveSpeed;
//                        decreaseMoveSpeed();
                    }
                } else {
                    playerCarY -= moveSpeed;
                    decreaseMoveSpeed();
                }
            }

            if (playerCarY <= 0) {
                System.out.println("WINNER WINNER");
                playerWon = true;
                gameOver = true;
            } else if (enemyCarY <= 0) {
                System.out.println("You lost... :(");
                enemyWon = true;
                gameOver = true;
            }

            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean randomChance20() {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        return randomNumber <= 20;
    }

}
