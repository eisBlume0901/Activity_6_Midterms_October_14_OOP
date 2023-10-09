package validator;

import pojo.UserNumber;

import java.util.Scanner;

import static java.lang.System.*;

public class NumberValidator implements ValidationMethod
{
    private Scanner scanner = new Scanner(in);
    private UserNumber userNumber;

    @Override
    public boolean validate() {

        out.println("Favorite Number: ");
        int favoriteNumber = Integer.parseInt(scanner.nextLine());
        out.println("Preferred Number of Children: ");
        int preferredNumber = Integer.parseInt(scanner.nextLine());

        if (preferredNumber >= 0)
        {
            userNumber =  new UserNumber();
            userNumber.setFavoriteNumber(favoriteNumber);
            userNumber.setPreferredNumberOfChildren(preferredNumber);
            return true;
        }
        return false;
    }
}
