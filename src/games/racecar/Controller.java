package games.racecar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Car playerCar;

    public Controller(Car Player) {
        this.playerCar = Player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_D:
                playerCar.state = Car.States.GAS;
                break;

            case KeyEvent.VK_SPACE:
                playerCar.state = Car.States.BRAKES;
                break;

            case KeyEvent.VK_SHIFT:
                if (playerCar.currentGear < 6 && !playerCar.isAutomatic) {
                    playerCar.currentGear += 1;
                }
                break;

            case KeyEvent.VK_CONTROL:
                if (playerCar.currentGear > 0 && !playerCar.isAutomatic) {
                    playerCar.currentGear -= 1;
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_D, KeyEvent.VK_SPACE:
                playerCar.state = Car.States.IDLE;
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Thing is unused, but I can't delete it or code will break.
    }

}
