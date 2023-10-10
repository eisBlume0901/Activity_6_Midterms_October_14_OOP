package logic.reportGenerator;

import java.util.*;
import java.util.regex.*;

public class StringProcessor
{
    private boolean isVowel(char ch)
    {
        String vowelsRegex = "[aeiouAEIOU]";
        String input = Character.toString(ch);
        Pattern pattern = Pattern.compile(vowelsRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean isConsonant(char ch)
    {
        return Character.isLetter(ch) && !isVowel(ch);
    }

    public int countVowels(String str)
    {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isVowel(ch)) {
                count++;
            }
        }
        return count;
    }

    public int countConsonants(String str)
    {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isConsonant(ch)) {
                count++;
            }
        }
        return count;
    }

    public int countWords(String str)
    {
        List<String> words = List.of(str.split("\\s+"));
        return words.size();
    }

    public String toBinary(int number) {
        return Integer.toBinaryString(number);
    }

    public String toOctal(int number) {
        return Integer.toOctalString(number);
    }

    public String toHexadecimal(int number) {
        return Integer.toHexString(number);
    }

    public int countLettersInText(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                count++;
            }
        }
        return count;
    }

    public String formatParagraph(String paragraph) {
        StringBuilder formattedText = new StringBuilder();
        String[] lines = paragraph.split("\n");
        int currentLineLength = 0;
        for (String line : lines) {
            if (currentLineLength + line.length() > 300) {
                formattedText.append("\n");
                currentLineLength = 0;
            }
            formattedText.append(line).append("\n");
            currentLineLength += line.length();
        }
        return formattedText.toString();
    }
}
