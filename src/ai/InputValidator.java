package ai;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidator
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        if (isValidName(name)) {
            System.out.println("Name is valid.");
            // Proceed with further processing or storage of the name
        } else {
            System.out.println("Invalid name. Please enter a valid name.");
        }

        scanner.close();
    }

    public static boolean isValidName(String name) {
        // Define the regular expression pattern for name validation
        String regex = "^(?i)(?:(?!.*([aeiou])\\1{1,}))(?:(?!.*([bcdfghjklmnpqrstvwxyz])\\2{1,}))(?:(?!.*[^a-zA-Z0-9\\s]))[a-zA-Z]+$\n";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(name);

        // Check if the input name matches the pattern
        return matcher.matches();
    }
}
