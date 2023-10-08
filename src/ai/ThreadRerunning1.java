package ai;

import java.util.Scanner;

public class ThreadRerunning1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxEntries = 2; // Number of entries to validate
        int maxReruns = 5;
        int baseSleepTime = 10000; // Initial sleep time in milliseconds (10 seconds)
        int maxSleepTime = 40000; // Maximum sleep time after 5 iterations (40 seconds)
        int countdownIncrease = 5000; // Countdown increase for each unsuccessful attempt

        boolean allEntriesValid = true;

        for (int entryCount = 1; entryCount <= maxEntries; entryCount++) {
            boolean validInput = false;
            int rerunCount = 0;
            int sleepTime = baseSleepTime;

            while (!validInput && rerunCount < maxReruns) {
                if (rerunCount > 0) {
                    sleepTime += countdownIncrease;
                    if (sleepTime > maxSleepTime) {
                        sleepTime = maxSleepTime;
                    }
                    System.out.print("Entry " + entryCount + ": Input validation failed. Rerunning in ");
                    countdownTimer(sleepTime / 1000); // Countdown timer
                    System.out.println(" seconds...");
                }

                System.out.print("Entry " + entryCount + ": Enter your input: ");
                String userInput = scanner.nextLine();

                // Implement your validation logic here
                validInput = validateInput(userInput);

                rerunCount++;
            }

            if (!validInput) {
                System.out.println("Entry " + entryCount + ": Input validation failed after " + rerunCount + " reruns. Exiting.");
                allEntriesValid = false;
                break; // Exit the program if input is invalid for any entry
            }
        }

        if (allEntriesValid) {
            System.out.println("All entries are valid. Proceed with the program.");
            // Proceed with the program for all valid entries
        }

        scanner.close();
    }

    // Simulate input validation logic
    private static boolean validateInput(String input) {
        // Implement your validation logic here
        // For demonstration purposes, allow only "Kate" as valid input
        return input.equals("Kate");
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
