package logic.reportGenerator;


import logic.EntryValidator;
import logic.validator.InputValidator;
import pojo.Person;

import java.time.LocalDate;
import java.time.MonthDay;

public class ReportGenerator implements InputValidator
{
    private StringProcessor stringProcessor;
    private UserInfoAnalyzer userInfoAnalyzer;


    public ReportGenerator()
    {
        stringProcessor = new StringProcessor();
        userInfoAnalyzer = new UserInfoAnalyzer();
    }
    public void displayReport(Person person)
    {
        StringBuilder report = new StringBuilder();
        String fullName = person.getName().toString();
        int consonantCount = stringProcessor.countConsonants(fullName);
        int vowelCount = stringProcessor.countVowels(fullName);

        report.append("Comprehensive Report:\n");
        report.append("    ").append(fullName).append(", whose name has ").append(consonantCount)
                .append(" consonants, ").append(vowelCount).append(" vowels,");

        int wordCount = stringProcessor.countWords(fullName);
        report.append(" and has ").append(wordCount).append(" words");

        int age = calculateAge(LocalDate.of(
                person.getBirthDatePlace().getBirthYear(),
                person.getBirthDatePlace().getBirthMonth(),
                person.getBirthDatePlace().getBirthDay()));
        String ageCategory = userInfoAnalyzer.determineAgeCategory(age);
        report.append(", is ").append(age).append(" years old, and falls into the ").append(ageCategory).append(" category. ");

        int favoriteNumber = person.getUserNumber().getFavoriteNumber();
        String binaryValue = stringProcessor.toBinary(favoriteNumber);
        String octalValue = stringProcessor.toOctal(favoriteNumber);
        String hexadecimalValue = stringProcessor.toHexadecimal(favoriteNumber);
        report.append(fullName).append("'s favorite number is ").append(favoriteNumber).append(", which has a binary value of ").append(binaryValue).append(", an octal value of ").append(octalValue).append(", and a hexadecimal value of ").append(hexadecimalValue).append(". ");


        MonthDay birthdate = MonthDay.of(person.getBirthDatePlace().getBirthMonth(), person.getBirthDatePlace().getBirthDay());
        String zodiacSign = userInfoAnalyzer.findZodiacSign(birthdate);

        report.append(fullName).append("'s birth date is on ").append(birthdate);
        if (zodiacSign != null) {
            report.append(", which makes their Zodiac sign a(n) ").append(zodiacSign).append(". ");
        } else {
            report.append("(Zodiac Sign: Invalid birthdate or not found.)");
        }

        int numberOfChildren = person.getUserNumber().getPreferredNumberOfChildren();
        userInfoAnalyzer.providePsychologicalFeedback(report, numberOfChildren);

        int reportLetterCount = stringProcessor.countLettersInText(report.toString());

        int lettersPerLine = 300;
        for (int i = lettersPerLine; i < reportLetterCount; i += lettersPerLine) {
            report.insert(i, "\n");
            reportLetterCount++;
        }

        String formattedReport = stringProcessor.formatParagraph(report.toString());
        System.out.println(formattedReport);
    }


}
