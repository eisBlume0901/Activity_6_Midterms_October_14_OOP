package validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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

    default Map<String, Map<List<String>, Map<List<String>, List<String>>>> storeMoviesToMap() {
        File movieFile = new File("Movies");
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = new HashMap<>();

        try {
            Files.lines(Paths.get(movieFile.getAbsolutePath()))
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        String year = parts[0].trim();
                        String title = parts[1].trim();

                        List<String> genres = extractGenres(parts[2], "Genre:"); // Updated method name
                        List<String> mainCharacters = extractCharacters(parts[3], "Main Characters:");
                        List<String> supportingCharacters = extractCharacters(parts[4], "Supporting Characters:");

                        Map<List<String>, List<String>> charactersMap = new HashMap<>();
                        charactersMap.put(mainCharacters, supportingCharacters);

                        Map<List<String>, Map<List<String>, List<String>>> genreCharacterMap = new HashMap<>();
                        genreCharacterMap.put(genres, charactersMap);

                        movieMap.put(title + " " + year, genreCharacterMap);

                    });

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return movieMap;
    }

    // Use this as inspiration for extractGenres
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

    private List<String> extractGenres(String genresString, String label) {
        return Optional.of(genresString)
                .filter(s -> s.contains(label))
                .map(s -> s.split(label))
                .filter(parts -> parts.length > 1)
                .map(parts -> Arrays.stream(parts[1].split(";"))
                        .map(String::trim)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    default boolean isMainCharacter(String movie, String movieCharacter)
    {
        List<String> mainCharacters = getCharactersForMovie(movie);
        if (mainCharacters.contains(movieCharacter))
        {
            return true;
        }
        return false;
    }

    default boolean isSupportingCharacter(String movie, String movieCharacter)
    {
        List<String> supportingCharacters = getSupportingCharacters(movie);
        if (supportingCharacters.contains(movieCharacter))
        {
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
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();

        return movieMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(movieTitle))
                .findFirst()
                .map(entry -> { // updating iteration since it is a list
                    Map<List<String>, List<String>> charactersMap = entry.getValue().values().iterator().next();
                    List<String> mainCharacters = charactersMap.keySet().iterator().next();
                    List<String> supportingCharacters = charactersMap.values().iterator().next();

//                    System.out.println("Main Characters: " + mainCharacters);
//                    System.out.println("Supporting Characters: " + supportingCharacters);

                    return mainCharacters; // Return main characters as an example
                })
                .orElse(Collections.emptyList()); // Return an empty list if the movie title is not found
    }

    private List<String> getSupportingCharacters(String movieTitle) {
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();
        Map<List<String>, Map<List<String>, List<String>>> genreCharacterMap = movieMap.get(movieTitle);

        if (genreCharacterMap != null) {
            for (Map.Entry<List<String>, Map<List<String>, List<String>>> entry : genreCharacterMap.entrySet()) { // updating iteration since it is a list
                Map<List<String>, List<String>> charactersMap = entry.getValue();
                for (Map.Entry<List<String>, List<String>> charactersEntry : charactersMap.entrySet()) {
                    List<String> mainCharacters = charactersEntry.getKey();
                    List<String> supportingCharacters = charactersEntry.getValue();

//                    System.out.println("Main Characters: " + mainCharacters);
//                    System.out.println("Supporting Characters: " + supportingCharacters);

                    return supportingCharacters; // Return main characters as an example
                }
            }
        }
        return Collections.emptyList(); // Return an empty list if the movie title is not found
    }

    default boolean isMonthDayValid(String monthName, int day)
    {
        Map<String, Integer> maxDaysMap = new HashMap<>();
        maxDaysMap.put("January", 31);
        maxDaysMap.put("February", 28); // Adjust for leap years
        maxDaysMap.put("March", 31);
        maxDaysMap.put("April", 30);
        maxDaysMap.put("May", 31);
        maxDaysMap.put("June", 30);
        maxDaysMap.put("July", 31);
        maxDaysMap.put("August", 31);
        maxDaysMap.put("September", 30);
        maxDaysMap.put("October", 31);
        maxDaysMap.put("November", 30);
        maxDaysMap.put("December", 31);

        if (maxDaysMap.containsKey(monthName))
        {
            int maxDays = maxDaysMap.get(monthName);
            if (isValidDay(day, maxDays)) {
                System.out.println("Valid date");
                return true;
            }
            else
            {
                System.out.println("Invalid day for " + monthName);
            }
        }
        else
        {
            System.out.println("Invalid month name");
        }
        return false;
    }

    private boolean isValidDay(int day, int maxDays)
    {
        return day >= 1 && day <= maxDays;
    }
    default Month getMonthNumber(String monthName)
    {
        Month month = Month.valueOf(monthName.toUpperCase());
        return month;
    }

    default boolean isYearValid(int year)
    {
        int minYear = 1900;
        int maxYear = LocalDate.now().getYear();

        if (year >= minYear && year <= maxYear)
            return true;
        return false;
    }
    default int calculateAge(LocalDate birthdate)
    {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdate, currentDate);
        return period.getYears();
    }
}
