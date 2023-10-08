package validator;

import java.util.Scanner;
import static java.lang.System.*;

public class BirthDateAndPlaceValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);

    @Override
    public boolean validate()
    {
        out.println("Birth Month: ");
        String birthMonth = scanner.nextLine();
        out.println("Birth Place: "); // City
        String birthPlace = scanner.nextLine();

        if (cityExists(birthPlace))
        {
            out.println("");
            return true;
        }
        return false;
    }
}
