package userinterface;
import logic.InputValidator;
import logic.Searcher;

import java.util.*;

import static java.lang.System.*;

public class UserInterface implements InputValidator, Searcher
{
    private Scanner scanner;
    public void start()
    {

    }

    public void inputValidation()
    {

    }

    public void askName()
    {
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Middle name: ");
        String lastName = scanner.nextLine();
    }
}
