package validator;

import pojo.Address;

import java.util.*;
import static java.lang.System.*;

public class AddressValidator implements ValidationMethod, InputValidator, Searcher
{
    private Scanner scanner = new Scanner(in);
    private Address address;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean validate()
    {
        out.println(ANSI_GREEN + "Barangay: ");
        String barangayName = scanner.nextLine();
        out.println("City: " + ANSI_RESET);
        String cityName = scanner.nextLine();

        if (isPhraseValid(barangayName) && cityExists(cityName))
        {
            address = new Address();
            address.setBarangay(barangayName);
            address.setCity(cityName);
            setAddress(address);
            return true;
        }
        return false;
    }

}


