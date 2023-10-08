package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;
public interface InputValidator
{

    default boolean isStringInputValid(String input)
    {
        String regex = "^(?i)(?:(?!.*([aeiou])\\1{1,}))(?:(?!.*([bcdfghjklmnpqrstvwxyz])\\2{1,}))(?:(?!.*[^a-zA-Z0-9\\s]))[a-zA-Z]+$\n";
        /* Not allowed:
        Repeating consonants
        Repeating vowels (exception example: Aaron)
        Numbers and special characters
        White space
        Consecutive consonants (exception example: Kate, Clarisse, Chloe)
        Consecutive vowels (exception example: Iah)
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
}
