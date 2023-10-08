package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
public interface InputValidator
{

    default boolean isStringInputValid(String input)
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
        Set<String> englishWordSet = storeEnglishWordsToSet();
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

//    default boolean isDateValid(LocalDate localDate)
//    {
//
//    }
}
