package ai;

import java.util.*;

public class MultipleEntriesValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] entryTypes = {"Name", "Address", "Favorites", "Birthdate", "Birthplace"};
        Map<String, Validator> validators = new HashMap<>();

        // Initialize validators for each entry type
        validators.put("Name", input -> validateName(input));
        validators.put("Address", input -> validateAddress(input));
        validators.put("Favorites", input -> validateFavorites(input));
        validators.put("Birthdate", input -> validateBirthdate(input));
        validators.put("Birthplace", input -> validateBirthplace(input));

        for (String entryType : entryTypes) {
            boolean validInput = false;
            int rerunCount = 0;

            while (!validInput && rerunCount < 5) { // You can adjust the maximum rerun count
                if (rerunCount > 0) {
                    System.out.println(entryType + " validation failed. Rerunning after 20 seconds...");
                    countdownTimer(20); // Countdown timer
                }

                System.out.print("Enter your " + entryType + ": ");
                String userInput = scanner.nextLine();

                // Use the appropriate validator for the current entry type
                validInput = validators.get(entryType).validate(userInput);

                rerunCount++;
            }

            if (!validInput) {
                System.out.println(entryType + " validation failed after " + rerunCount + " reruns. Exiting.");
                break; // Exit the program if input is invalid for any entry
            }
        }

        // Proceed with the program if all entries are valid
        System.out.println("All entries are valid. Proceed with the program.");

        scanner.close();
    }

    // Define validator interfaces
    interface Validator {
        boolean validate(String input);
    }

    // Validation methods for different entry types (customize as needed)
    private static boolean validateName(String input) {
        // Implement name validation logic here
        // For demonstration purposes, allow only names with at least 3 characters
        return input.length() >= 3;
    }

    private static boolean validateAddress(String input) {
        // Implement address validation logic here
        // Example: Check if the address is in a valid format
        return input.matches("^[0-9]+\\s+[A-Za-z\\s]+");
    }

    private static boolean validateFavorites(String input) {
        // Implement favorites validation logic here
        // Example: Check if the input matches a list of valid favorites
        List<String> validFavorites = Arrays.asList("Movie", "Food", "Color");
        return validFavorites.contains(input);
    }

    private static boolean validateBirthdate(String input) {
        // Implement birthdate validation logic here
        // Example: Check if the input matches a valid date format
        return input.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private static boolean validateBirthplace(String input) {
        // Implement birthplace validation logic here
        // Example: Check if the input is a valid place name
        return !input.isEmpty(); // Placeholder validation
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
