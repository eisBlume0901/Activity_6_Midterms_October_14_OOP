package games.racecar;
import java.util.Scanner;

public class RaceCarGame {

    public static Scanner scanner = new Scanner(System.in);
    public static int trackHeight;
    public static boolean isAutomatic = true;
    public static String modeChoice;

    public void run() {

        System.out.print("Is your car AUTOMATIC or MANUAL?: ");
        modeChoice = scanner.nextLine();

        if (modeChoice.equalsIgnoreCase("Manual")) {
            isAutomatic = false;
            raceTrackLength();
        } else if (modeChoice.equalsIgnoreCase("Automatic")) {
            isAutomatic = true;
            raceTrackLength();
        } else {
            System.out.print("Invalid Choice, Try again. ");
            run();
        }
    }

    public void raceTrackLength() {

        try {
            System.out.print("Enter the length of the racetrack (in meters): ");
            trackHeight = scanner.nextInt();

            RaceCar game = new RaceCar(trackHeight, isAutomatic);
            Thread gameThread = new Thread(game);
            gameThread.start();

        } catch(Exception e) {
            System.out.println("Invalid Input");
        }
    }

}