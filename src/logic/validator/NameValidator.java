package logic.validator;

import pojo.Name;

import java.util.*;
import static java.lang.System.*;

public class NameValidator implements ValidationMethod, InputValidator
{
    private Scanner scanner = new Scanner(in);
    private Name name;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean validate()
    {
        out.println(ANSI_YELLOW + "Personal Details");
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Middle name: ");
        String middleName = scanner.nextLine();
        out.println("Last name: " + ANSI_RESET);
        String lastName = scanner.nextLine();

        if ( isPhraseValid(firstName) &&
                isSentenceValid(firstName) &&
                isPhraseValid(middleName) &&
                isPhraseValid(lastName) )
        {
            name = new Name();
            name.setFirstName(firstName);
            name.setMiddleName(middleName);
            name.setLastName(lastName);
            setName(name);
            return true;
        }
        return false;
    }

}
