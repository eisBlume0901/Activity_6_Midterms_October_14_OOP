package games.racecar;

import java.util.Random;

public class Car {

    public States state;
    public boolean hasWon;
    public int currentGear;
    public int carX;
    public int carY;
    public boolean isAutomatic;
    public boolean isBot;
    public int moveSpeed = 0;
    public boolean hasOverheated = false;
    private int acceleration = 1;
    public int maxSpeed = 20;
    private static int minSpeed = 0;

    public Car(boolean isAutomatic, boolean isBot) {

        this.isAutomatic = isAutomatic;
        this.state = States.IDLE;
        this.isBot = isBot;
        this.hasOverheated = false;

    }

    public enum States {
        IDLE,
        GAS,
        BRAKES
    }



    public void stateMachine() {

        if (!isBot) {

            if (!hasOverheated) {
            switch (state) {

                case IDLE:

                    decelerate();

                    break;

                case GAS:

                    accelerate();

                    break;

                case BRAKES:

                    brake();

                    break;
            }
            }
            else
            {
                decelerate();
            }

        } else {

            if (!hasOverheated) {
                accelerate();
            } else {
                decelerate();
            }

        }

        move();
        gearShift();
        overheatChance();

        if (hasOverheated) {
            state = States.IDLE;
        }

    }

    public void gearShift() {

        if (isAutomatic) {
            currentGear = Math.min((moveSpeed / 20) + 1, 6);
        } else {
            currentGear = Math.min(currentGear, 6);
        }

        maxSpeed = currentGear * 20;

    }

    public void overheatChance() {
        int[] speeds = {99, 119};

        for (int speed : speeds) {
            if (moveSpeed == speed && randomChance20()) {
                hasOverheated = true;
                break;
            }
        }
    }

    public void move() {
        carX += moveSpeed;

        if (moveSpeed < 0) { moveSpeed = 0; }

        if (moveSpeed > maxSpeed) { moveSpeed = maxSpeed; }
    }

    public void accelerate() {
        if (moveSpeed < maxSpeed) {
            moveSpeed += acceleration;
        }
    }

    public void decelerate() {
        if (moveSpeed > minSpeed) {
            moveSpeed -= acceleration;
        }
    }

    public void brake() {
        if (moveSpeed > minSpeed) {
            moveSpeed -= acceleration + 3;
        }
    }

    public static boolean randomChance20() {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        return randomNumber <= 20;
    }

}
