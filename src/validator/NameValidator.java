package validator;

import domain.Name;

import java.util.*;
import static java.lang.System.*;

public class NameValidator implements ValidationMethod, InputValidator
{
    private Scanner scanner = new Scanner(in);
    private Name name;
    @Override
    public boolean validate()
    {
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Middle name: ");
        String middleName = scanner.nextLine();
        out.println("Last name: ");
        String lastName = scanner.nextLine();

        if ( (isPhraseValid(firstName) && isSentenceValid(firstName)) &&
                isStringInputValid(middleName) &&
                isStringInputValid(lastName) )
        {
            name = new Name();
            name.setFirstName(firstName);
            name.setMiddleName(middleName);
            name.setLastName(lastName);
            out.println(name);
            return true;
        }
        return false;
    }
}
