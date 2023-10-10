package logic.validator;

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

    private Map<String, Map<List<String>, Map<List<String>, List<String>>>> storeMoviesToMap() {
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

    private List<String> extractCharacters(String charactersString, String label)
    {
        return Optional.of(charactersString)
                .filter(s -> s.contains(label))
                .map(s -> s.split(label))
                .filter(parts -> parts.length > 1)
                .map(parts -> {
                    return Arrays.stream(parts[1].split(";"))
                            .map(s -> s.trim())
                            .collect(Collectors.toList());
                })
                .orElse(Collections.emptyList());
    }

    private List<String> extractGenres(String genresString, String label) {
        return Optional.of(genresString)
                .filter(s -> s.contains(label))
                .map(s -> s.split(label))
                .filter(parts -> parts.length > 1)
                .map(parts -> Arrays.stream(parts[1].split(";"))
                        .map(s -> s.trim())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    default boolean isMainCharacter(String movie, String movieCharacter)
    {
        List<String> mainCharacters = getCharactersForMovie(movie);
        for (String character : mainCharacters)
            if (character.equalsIgnoreCase(movieCharacter))
                return true;
        return false;
    }

    default boolean isSupportingCharacter(String movie, String movieCharacter)
    {
        List<String> supportingCharacters = getSupportingCharacters(movie);
        for (String character : supportingCharacters)
            if (character.equalsIgnoreCase(movieCharacter))
                return true;
        return false;
    }

    default boolean isMovieCharacter(String movie, String movieCharacter)
    {
        List<String> characterList = allCharactersFromAMovie(movie);
        for (String character : characterList)
            if (character.equalsIgnoreCase(movieCharacter))
                return true;
        return false;
    }

    default List<String> allCharactersFromAMovie(String movie)
    {
        List<String> mainCharacters = getCharactersForMovie(movie);
        List<String> supportingCharacters = getSupportingCharacters(movie);
        List<String> allCharacters = new ArrayList<>(mainCharacters);
        allCharacters.addAll(supportingCharacters);
        return allCharacters;
    }

    default List<String> getCharactersForMovie(String movieTitle) {
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();

        return movieMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(movieTitle))
                .findFirst()
                .map(entry -> {
                    Map<List<String>, List<String>> charactersMap = entry.getValue().values().iterator().next();
                    List<String> mainCharacters = new ArrayList<>(charactersMap.keySet().iterator().next());
                    return mainCharacters;
                })
                .orElse(Collections.emptyList());
    }

    default List<String> getSupportingCharacters(String movieTitle) {
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();

        return movieMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(movieTitle))
                .findFirst()
                .map(entry -> {
                    Map<List<String>, List<String>> charactersMap = entry.getValue().values().iterator().next();
                    List<String> mainCharacters = new ArrayList<>(charactersMap.values().iterator().next());
                    return mainCharacters;
                })
                .orElse(Collections.emptyList());
    }

    default List<String> getGenre(String movieTitle) {
        Map<String, Map<List<String>, Map<List<String>, List<String>>>> movieMap = storeMoviesToMap();

        return movieMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(movieTitle))
                .findFirst()
                .map(entry -> {
                    Map<List<String>, Map<List<String>, List<String>>> genreCharacterMap = entry.getValue();
                    List<String> genres = new ArrayList<>(entry.getValue().keySet().iterator().next());
                    return genres;
                })
                .orElse(Collections.emptyList());
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

        if (maxDaysMap.containsKey(monthName)) {
            int maxDays = maxDaysMap.get(monthName);
            if (isValidDay(day, maxDays))
                return true;
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
