package ai;

import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.*;

public class WordValidator
{
    private File file;
    private Set englishWordSet;

    public WordValidator()
    {
        this.file = new File("EnglishWords");
        this.englishWordSet = new HashSet();
    }
    public void storeEnglishWordsToSet()
    {
        try
        {
            Files.lines(Paths.get(file.getAbsolutePath()))
                    .forEach(line -> englishWordSet.add(line));

            System.out.println("Successfully added!");
            System.out.println(englishWordSet.size());
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public boolean isEnglishWord(String input)
    {
        if (englishWordSet.contains(input))
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        WordValidator wv = new WordValidator();
        wv.storeEnglishWordsToSet();
        System.out.println(wv.isEnglishWord("abcdefg"));
        System.out.println(wv.isEnglishWord("virgo"));
        System.out.println(wv.isEnglishWord("city"));
        System.out.println(wv.isEnglishWord("omelette"));
        System.out.println(wv.isEnglishWord("pizza"));
    }
}
