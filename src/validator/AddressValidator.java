package validator;

import java.util.*;
import static java.lang.System.*;

public class AddressValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);

    @Override
    public boolean validate()
    {
        out.println("Barangay: ");
        String barangayName = scanner.nextLine();
        out.println("City: ");
        String cityName = scanner.nextLine();

        // East Rembo is a unique word
        if (isPhraseValid(barangayName) && cityExists(cityName))
        {
            out.println("Address valid!");
            return true;
        }
        return false;
    }
}


