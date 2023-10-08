package validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public interface InputValidator
{

    default boolean isStringInputValid(String input) // set to private
    {
        String regex = "^(?!.*([aeiouAEIOU])\\1{2,}|.*[^a-zA-Z]+|.*[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ]{4}|.*[aeiouAEIOU]{3})[A-Za-z]*$";
        /* Not allowed:
        Repeating consonants
        Repeating vowels (exception example: Aaron)
        Numbers and special characters
        White space
        Consecutive consonants (exception example: Kate, Clarisse, Chloe)
        Consecutive vowels (exception example: Iah)
        Space is not allowed
         */
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private Set storeEnglishWordsToSet()
    {
        Set<String> englishWordSet = new HashSet<>();
        File englishWordFile = new File("EnglishWords");
        try
        {
            Files.lines(Paths.get(englishWordFile.getAbsolutePath()))
                    .forEach(line -> englishWordSet.add(line));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return englishWordSet;
    }
    default boolean isEnglishWord(String input)
    {
        Set englishWordSet = storeEnglishWordsToSet();
        return englishWordSet.contains(input.toLowerCase());
    }

    default boolean isSentenceValid(String input)
    {
        String[] words = input.split("\\s+");
        boolean isValid = true;

        for (String word : words)
        {
            String wordOnly = word.replaceAll("[^a-zA-Z]", "");
            if (!isEnglishWord(wordOnly))
            {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    default boolean isPhraseValid(String input)
    {
        String[] tokens = input.split("\\s+");
        boolean isValid = true;

        for (String token : tokens)
        {
            if (!isStringInputValid(token))
            {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private Map storeMoviesToMap()
    {
        File movieFile = new File("Movies");
        Map<String, Map<String, Map<List<String>, List<String>>>> movieMap = new HashMap<>();

        try {
            Files.lines(Paths.get(movieFile.getAbsolutePath()))
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        String year = parts[0].trim();
                        String title = parts[1].trim();
                        String genre = parts[2].trim();

                        List<String> mainCharacters = extractCharacters(parts[3], "Main Characters:");
                        List<String> supportingCharacters = extractCharacters(parts[4], "Supporting Characters:");

                        Map<List<String>, List<String>> charactersMap = new HashMap<>();
                        charactersMap.put(mainCharacters, supportingCharacters);

                        Map<String, Map<List<String>, List<String>>> genreCharacterMap = new HashMap<>();
                        genreCharacterMap.put(genre, charactersMap);

                        movieMap.put(title, genreCharacterMap);
                    });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return movieMap;
    }

    private List<String> extractCharacters(String charactersString, String label)
    {
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


    default boolean isMainCharacter()
    {

        return false;
    }

    default boolean isSupportingCharacter()
    {
        return false;
    }
    default boolean isMovieYearValid(int year) {
        if (year >= 2000 && year <= 2023) {
            return true;
        }
        return false;
    }

    default boolean isMovieCharacter(String movie, String movieCharacter)
    {
        List<String> characterList = allCharactersFromAMovie(movie);

        return characterList.contains(movieCharacter);
    }

    private List<String> allCharactersFromAMovie(String movie)
    {
        List<String> mainCharacters = getCharactersForMovie(movie);
        List<String> supportingCharacters = getSupportingCharacters(movie);
        List<String> allCharacters = new ArrayList<>(mainCharacters);
        allCharacters.addAll(supportingCharacters);
        return allCharacters;
    }
    private List<String> getCharactersForMovie(String movieTitle) {
        Map<String, Map<String, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();

        return movieMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(movieTitle))
                .findFirst()
                .map(entry -> {
                    Map<List<String>, List<String>> charactersMap = entry.getValue().values().iterator().next();
                    List<String> mainCharacters = charactersMap.keySet().iterator().next();
                    List<String> supportingCharacters = charactersMap.values().iterator().next();

                    // You can now work with mainCharacters and supportingCharacters as needed
                    System.out.println("Main Characters: " + mainCharacters);
                    System.out.println("Supporting Characters: " + supportingCharacters);

                    return mainCharacters; // Return main characters as an example
                })
                .orElse(Collections.emptyList()); // Return an empty list if the movie title is not found
    }



    private List<String> getSupportingCharacters(String movieTitle) {
        Map<String, Map<String, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();
        Map<String, Map<List<String>, List<String>>> genreCharacterMap = movieMap.get(movieTitle);

        if (genreCharacterMap != null) {
            for (Map.Entry<String, Map<List<String>, List<String>>> entry : genreCharacterMap.entrySet()) {
                Map<List<String>, List<String>> charactersMap = entry.getValue();
                for (Map.Entry<List<String>, List<String>> charactersEntry : charactersMap.entrySet()) {
                    List<String> mainCharacters = charactersEntry.getKey();
                    List<String> supportingCharacters = charactersEntry.getValue();

                    // You can now work with mainCharacters and supportingCharacters as needed
                    System.out.println("Main Characters: " + mainCharacters);
                    System.out.println("Supporting Characters: " + supportingCharacters);

                    return supportingCharacters; // Return main characters as an example
                }
            }
        }
        return Collections.emptyList(); // Return an empty list if the movie title is not found
    }

}
