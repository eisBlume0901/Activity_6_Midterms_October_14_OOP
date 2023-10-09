package validator;

import pojo.Address;

import java.util.*;
import static java.lang.System.*;

public class AddressValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);
    private Address address;

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
            address = new Address();
            address.setBarangay(barangayName);
            address.setCity(cityName);
            return true;
        }
        return false;
    }
}


