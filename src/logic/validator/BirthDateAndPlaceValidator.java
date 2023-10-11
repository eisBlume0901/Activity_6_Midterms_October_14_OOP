package logic.validator;

import pojo.BirthDateAndPlace;

import java.util.*;
import static java.lang.System.*;

public class BirthDateAndPlaceValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);
    private BirthDateAndPlace birthDateAndPlace;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public BirthDateAndPlace getBirthDateAndPlace() {
        return birthDateAndPlace;
    }

    public void setBirthDateAndPlace(BirthDateAndPlace birthDateAndPlace) {
        this.birthDateAndPlace = birthDateAndPlace;
    }

    @Override
    public boolean validate()
    {
        out.println(ANSI_YELLOW + "Birth month: ");
        String birthMonth = scanner.nextLine();
        out.println("Birth day: ");
        String birthDay = scanner.nextLine();
        out.println("Birth year: ");
        String birthYear = scanner.nextLine();
        out.println("Birth place: " + ANSI_RESET); // City
        String birthPlace = scanner.nextLine();

        if (birthDay.matches("-?\\d+") &&
                isMonthDayValid(birthMonth, Integer.parseInt(birthDay)) &&
                birthYear.matches("-?\\d+") &&
                isYearValid(Integer.parseInt(birthYear)) &&
                cityExists(birthPlace))
        {
            birthDateAndPlace = new BirthDateAndPlace();
            birthDateAndPlace.setBirthMonth(getMonthNumber(birthMonth));
            birthDateAndPlace.setBirthDay(Integer.parseInt(birthDay));
            birthDateAndPlace.setBirthYear(Integer.parseInt(birthYear));
            birthDateAndPlace.setBirthPlace(birthPlace);
            setBirthDateAndPlace(birthDateAndPlace);
            return true;
        }
        return false;
    }

}
