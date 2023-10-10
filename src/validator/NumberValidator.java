package validator;

import pojo.Person;
import pojo.UserNumber;

import java.util.Scanner;

import static java.lang.System.*;

public class NumberValidator implements ValidationMethod
{
    private Scanner scanner = new Scanner(in);
    private UserNumber userNumber;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";



    public UserNumber getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(UserNumber userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public boolean validate() {

        out.println(ANSI_BLUE + "Number: " + ANSI_RESET);
        int favoriteNumber = Integer.parseInt(scanner.nextLine());
        out.println(ANSI_PURPLE + "\nPreferred Number of Children: " + ANSI_RESET);
        int preferredNumber = Integer.parseInt(scanner.nextLine());

        if (preferredNumber >= 0)
        {
            userNumber =  new UserNumber();
            userNumber.setFavoriteNumber(favoriteNumber);
            userNumber.setPreferredNumberOfChildren(preferredNumber);
            setUserNumber(userNumber);
            return true;
        }
        return false;
    }

}
