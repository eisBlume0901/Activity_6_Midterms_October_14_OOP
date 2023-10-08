package userinterface;
import logic.InputValidator;
import logic.Searcher;
import java.util.*;
import static java.lang.System.*;

public class UserInterface implements InputValidator, Searcher
{
    private Scanner scanner;

    public UserInterface()
    {
        scanner = new Scanner(in);
    }
    public void start()
    {
        inputValidation();
    }

    private void inputValidation()
    {
        int maxReruns = 5;
        int baseSleepTime = 3000; // 15000 originally, 3000 is for testing purposes only
        int maxSleepTime = 40000;
        int entries = 3;
        int countdownIncrease = 5000;

        boolean allEntriesValid = true;

        for (int entryCount = 1; entryCount <= entries; entryCount++)
        {
            boolean validInput = false;
            int rerunCount = 0;
            int sleepTime = baseSleepTime;

            while (!validInput && rerunCount < maxReruns)
            {
                if (rerunCount > 0)
                {
                    sleepTime += countdownIncrease;
                    if (sleepTime > maxSleepTime)
                    {
                        sleepTime = maxSleepTime;
                    }
                    out.println("Entry: " + entryCount + "\nInput validation failed. Rerunning in");
                    countdownTimer(sleepTime / 1000);
                    out.print(" seconds...\n");
                }

                validInput = validateInput(entryCount);
                rerunCount++;
            }

            if (!validInput)
            {
                out.println("Entry " + entryCount + "\nInput validation failed after " + rerunCount + " reruns. Exiting...");
                allEntriesValid = false;
                break;
            }
        }

        if (allEntriesValid)
            out.println("All entries are valid.");
    }

    private void countdownTimer(int seconds)
    {
        for (int i = seconds; i >= 1; i--)
        {
            out.print(i + " ");
            sleep(1000);
        }
        out.println();
    }

    private void sleep(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }

    private boolean validateInput(int entryCount)
    {
        boolean nameIsValid = isNameValid();
        boolean addressIsValid = isAddressValid();
        boolean movieIsValid = isMovieValid();
        boolean foodIsValid = isFoodValid();
        return nameIsValid && addressIsValid && movieIsValid && foodIsValid;
    }
    private boolean isNameValid()
    {
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Middle name: ");
        String middleName = scanner.nextLine();
        out.println("Last name: ");
        String lastName = scanner.nextLine();

        if ( (isStringInputValid(firstName) && isEnglishWord(firstName)) &&
              isStringInputValid(middleName) &&
              isEnglishWord(lastName) )
        {
            // set to Name class
            return true;
        }
        return false;
    }

    private boolean isAddressValid()
    {
        out.println("Barangay: ");
        String barangayName = scanner.nextLine();
        out.println("City: ");
        String cityName = scanner.nextLine();

        if (isStringInputValid(barangayName) && cityExists(cityName))
        {
            // set to Address class
            // should also search the region
            return true;
        }
        return false;
    }

    private boolean isMovieValid()
    {
        // Favorites sector
        out.println("Movie title: ");
        String movieName = scanner.nextLine();
        out.println("Movie character: ");
        String movieCharacter = scanner.nextLine();

        if (isSentenceValid(movieName) && isSentenceValid(movieCharacter))
        {
            // set to Movie class
            return true;
        }
        return false;
    }

    private boolean isFoodValid()
    {
        // Favorites sector
        out.println("Food: ");
        String foodName = scanner.nextLine();

        if (isSentenceValid(foodName))
        {
            return true;
        }
        return false;
    }

    private boolean isBirthPlaceAndDateValid()
    {
        out.println("Birth Month: ");
        return false;
    }
}
