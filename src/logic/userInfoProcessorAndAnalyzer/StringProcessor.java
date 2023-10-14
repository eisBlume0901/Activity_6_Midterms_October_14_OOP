package logic.userInfoProcessorAndAnalyzer;

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
        String[] words = paragraph.split("\\s+");
        int currentLineLength = 0;
        int currentParagraphLength = 0;
        int linesInParagraph = 0;

        for (String word : words) {
            if (currentLineLength + word.length() + 1 > 60) {
                formattedText.append("\n");
                currentLineLength = 0;
                linesInParagraph++;

                if (linesInParagraph >= 5 || currentParagraphLength >= 300) {
                    formattedText.append("\n\n");
                    currentParagraphLength = 0;
                    linesInParagraph = 0;
                }

                // Start a new line and add the current word
            }
            formattedText.append(word).append(" ");
            currentLineLength += word.length() + 1;
            currentParagraphLength += word.length() + 1;
        }

        return formattedText.toString();
    }
}
