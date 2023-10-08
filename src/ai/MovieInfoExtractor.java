package ai;

import java.util.*;
import java.util.stream.Collectors;

public class MovieInfoExtractor {
    public static void main(String[] args) {
        String input = "2004, Howl's Moving Castle, Fantasy, Main Characters: Howl; Sophie, Supporting Characters: Markl; Calcifer";

        // Split the input string by commas
        String[] parts = input.split(",");


        Map<String, Map<String, Map<List, List>>> movieMap = new HashMap<>();

        // Ensure we have enough parts (at least 4) to extract the information
        if (parts.length >= 4) {
            // Extract year, title, and genre
            String year = parts[0].trim();
            String title = parts[1].trim();
            String genre = parts[2].trim();

            // Extract main characters and supporting characters
            List<String> mainCharacters = extractCharacters(parts[3], "Main Characters:");
            List<String> supportingCharacters = extractCharacters(parts[4], "Supporting Characters:");

            // Output the separated movie info
            System.out.println("Year: " + year);
            System.out.println("Movie Title: " + title);
            System.out.println("Movie Genre: " + genre);
            System.out.println("Main Characters: " + mainCharacters);
            System.out.println("Supporting Characters: " + supportingCharacters);

            Map<List, List> charactersMap = new HashMap<>();
            charactersMap.put(mainCharacters, supportingCharacters);

            Map<String, Map<List, List>> genreCharacterMap = new HashMap<>();
            genreCharacterMap.put(genre, charactersMap);

            movieMap.put(title + " " + year, genreCharacterMap);

            System.out.println(movieMap);
        } else {
            System.out.println("Invalid input format.");
        }
    }

    // Helper method to extract characters into a list
    private static List<String> extractCharacters(String charactersString, String label) {
        // Use Optional to simplify conditional checks
        return Optional.of(charactersString)
                // Check if the label is present in the string
                .filter(s -> s.contains(label))
                // Split the string based on the label and get the second part
                .map(s -> s.split(label))
                .filter(parts -> parts.length > 1)
                // Split the second part by semicolons and trim the values
                .map(parts -> Arrays.stream(parts[1].split(";"))
                        .map(String::trim)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
