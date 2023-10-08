package ai;

import java.util.*;

public class ThreadRerunning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxReruns = 5;
        int baseSleepTime = 20000; // Initial sleep time in milliseconds (20 seconds)
        int maxSleepTime = 40000; // Maximum sleep time after 5 iterations (40 seconds)

        boolean validInput = false;
        int rerunCount = 0;

        while (!validInput && rerunCount < maxReruns) {
            if (rerunCount > 0) {
                int sleepTime = Math.min(baseSleepTime + rerunCount * 5000, maxSleepTime);
                System.out.print("Input validation failed. Rerunning in ");
                countdownTimer(sleepTime / 1000); // Countdown timer
                System.out.println(" seconds...");
            }

            System.out.print("Enter your input: ");
            String userInput = scanner.nextLine();

            // Implement your validation logic here
            validInput = validateInput(userInput);

            rerunCount++;
        }

        if (validInput) {
            System.out.println("Input validation succeeded.");
            // Proceed with the program
        } else {
            System.out.println("Input validation failed after " + rerunCount + " reruns. Exiting.");
        }

        scanner.close();
    }

    // Simulate input validation logic
    private static boolean validateInput(String input) {
        // Implement your validation logic here
        // For demonstration purposes, always return true
        if (input.equals("Kate"))
            return false;
        return true;
    }

    // Countdown timer method
    private static void countdownTimer(int seconds) {
        for (int i = seconds; i >= 1; i--) {
            System.out.print(i + " ");
            sleep(1000); // Sleep for 1 second
        }
        System.out.println();
    }

    // Sleep method to pause program execution
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
