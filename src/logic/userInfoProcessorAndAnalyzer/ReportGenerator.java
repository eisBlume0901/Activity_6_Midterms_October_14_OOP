package logic.userInfoProcessorAndAnalyzer;

import logic.validator.InputValidator;
import logic.validator.Searcher;
import pojo.Person;
import java.time.*;
import java.util.*;


public class ReportGenerator implements InputValidator, Searcher
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
        String firstName = person.getName().getFirstName();

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

        String barangay = person.getAddress().getBarangay();
        String city = person.getAddress().getCity();
        String region = searchRegion(person.getAddress().getCity());
        report.append(firstName).append(", who lives in ").append(barangay).append(", ").append(city).append(", ").append(region);

        String course = person.getCourse().getCourseName();
        String industry = searchIndustry(course);
        report.append(", is currently studying ").append(course).append(" in order to prepare for the ").append(industry).append(" industry. ");

        int favoriteNumber = person.getUserNumber().getFavoriteNumber();
        String binaryValue = stringProcessor.toBinary(favoriteNumber);
        String octalValue = stringProcessor.toOctal(favoriteNumber);
        String hexadecimalValue = stringProcessor.toHexadecimal(favoriteNumber);
        report.append(firstName).append("'s favorite number is ").append(favoriteNumber).append(", which has a binary value of ").append(binaryValue).append(", an octal value of ").append(octalValue).append(", and a hexadecimal value of ").append(hexadecimalValue).append(". ");

        Month birthMonth = person.getBirthDatePlace().getBirthMonth();
        int birthDay = person.getBirthDatePlace().getBirthDay();
        MonthDay birthdate = MonthDay.of(birthMonth, birthDay);

        String birthMonthProperCapitalization = birthMonth.toString().substring(0, 1).toUpperCase() + birthMonth.toString().substring(1).toLowerCase();
        report.append(firstName).append("'s birth date is on ").append(birthMonth).append(", ").append(birthDay);

        String zodiacSign = userInfoAnalyzer.findZodiacSign(birthdate);
        if (zodiacSign != null)
            report.append(", which makes their Zodiac sign a(n) ").append(zodiacSign).append(". ");
        else
            report.append("(Zodiac Sign: Invalid birthdate or not found.)");


        String movieTitle = person.getMovie().getMovieTitle();
        List<String> genreList = getGenre(person.getMovie().getMovieTitle());
        String genres = genreList.toString().substring(1, genreList.toString().length() - 1);
        report.append("The genre(s) of ").append(firstName).append("'s favorite movie, ").append(movieTitle).append(", is/are ").append(genres).append(". ");

        String movieCharacter = person.getMovie().getMovieCharacter();

        report.append(movieCharacter);

        if (isMainCharacter(movieTitle, movieCharacter))
            report.append(" is the main character").append(". ");
        if (isSupportingCharacter(movieTitle, movieCharacter))
            report.append(" is the supporting character").append(". ");

        int numberOfChildren = person.getUserNumber().getPreferredNumberOfChildren();
        report.append(userInfoAnalyzer.providePsychologicalFeedback(numberOfChildren));

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
