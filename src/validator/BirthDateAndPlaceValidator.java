package validator;

import pojo.BirthDateAndPlace;

import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;
import static java.lang.System.*;

public class BirthDateAndPlaceValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);
    private BirthDateAndPlace birthDateAndPlace;

    @Override
    public boolean validate()
    {
        out.println("Birth Month: ");
        String birthMonth = scanner.nextLine();
        out.println("Birth Day: ");
        int birthDay = Integer.parseInt(scanner.nextLine());
        out.println("Birth Year: ");
        int birthYear = Integer.parseInt(scanner.nextLine());
        out.println("Birth Place: "); // City
        String birthPlace = scanner.nextLine();

        if (isMonthDayValid(birthMonth, birthDay) &&
                isYearValid(birthYear) &&
                cityExists(birthPlace))
        {
            birthDateAndPlace = new BirthDateAndPlace();
            birthDateAndPlace.setBirthMonth(getMonthNumber(birthMonth));
            birthDateAndPlace.setBirthDay(birthDay);
            birthDateAndPlace.setBirthYear(birthYear);
            birthDateAndPlace.setBirthPlace(birthPlace);


            // For debugging have to remove
            out.println("Age: " + calculateAge(LocalDate.of(birthDateAndPlace.getBirthYear(),
                    birthDateAndPlace.getBirthMonth(), birthDateAndPlace.getBirthDay())));

            return true;
        }
        return false;
    }
}
