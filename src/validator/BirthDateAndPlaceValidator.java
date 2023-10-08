package validator;

import pojo.BirthDateAndPlace;
import java.util.Scanner;
import static java.lang.System.*;

public class BirthDateAndPlaceValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);
    private BirthDateAndPlace birthDateAndPlace;

    @Override
    public boolean validate()
    {


        out.println("Birth Place: "); // City
        String birthPlace = scanner.nextLine();

        if (cityExists(birthPlace))
        {
            birthPlace = new BirthDateAndPlace();
            return true;
        }
        return false;
    }
}
