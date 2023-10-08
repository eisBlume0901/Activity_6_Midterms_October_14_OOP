package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleEntriesValidation2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Entry> entries = new ArrayList<>();

        // Define the validation rules and input data for each entry
        entries.add(new Entry("Name", input -> isNameValid(input)));
        entries.add(new Entry("Address", MultipleEntriesValidation2::isAddressValid));
        entries.add(new Entry("Movie", MultipleEntriesValidation2::isMovieValid));

        int maxReruns = 5;
        int baseSleepTime = 3000;
        int maxSleepTime = 40000;
        int countdownIncrease = 5000;

        boolean allEntriesValid = true;

        for (Entry entry : entries) {
            boolean validInput = false;
            int rerunCount = 0;
            int sleepTime = baseSleepTime;

            while (!validInput && rerunCount < maxReruns) {
                if (rerunCount > 0) {
                    sleepTime += countdownIncrease;
                    if (sleepTime > maxSleepTime) {
                        sleepTime = maxSleepTime;
                    }
                    System.out.println("Entry: " + entry.getName() + "\nInput validation failed. Rerunning in");
                    countdownTimer(sleepTime / 1000);
                    System.out.print(" seconds...\n");
                }

                System.out.print("Entry " + entry.getName() + ": Enter your input: ");
                String userInput = scanner.nextLine();

                // Validate the input using the appropriate validation method for the entry
                validInput = entry.getValidationMethod().validate(userInput);
                rerunCount++;
            }

            if (!validInput) {
                System.out.println("Entry " + entry.getName() + "\nInput validation failed after " + rerunCount + " reruns. Exiting...");
                allEntriesValid = false;
                break;
            }
        }

        if (allEntriesValid) {
            System.out.println("All entries are valid.");
            // Proceed with the program.
        }

        scanner.close();
    }

    // Validation methods for different entry types
    private static boolean isNameValid(String input) {
        // Implement validation logic for names
        return input.equals("Kate");
    }

    private static boolean isAddressValid(String input) {
        // Implement validation logic for addresses
        return input.equals("123 Main St");
    }

    private static boolean isMovieValid(String input) {
        // Implement validation logic for movies
        return input.equals("Inception");
    }

    // Other methods (countdownTimer, sleep) as needed

    static class Entry {
        private final String name;
        private final ValidationMethod validationMethod;

        public Entry(String name, ValidationMethod validationMethod) {
            this.name = name;
            this.validationMethod = validationMethod;
        }

        public String getName() {
            return name;
        }

        public ValidationMethod getValidationMethod() {
            return validationMethod;
        }
    }

    // Functional interface for validation methods
    interface ValidationMethod {
        boolean validate(String input);
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
