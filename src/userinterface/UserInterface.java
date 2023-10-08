package userinterface;
import validator.*;

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
        if (validateEntries())
        {
            out.println("All done");
        }
        else
        {
            out.println("Wrong entries bye bye");
        }
    }

    private boolean validateEntries()
    {
        List<ValidationMethod> entries = new ArrayList<>();
        int maxReruns = 5;
        int baseSleepTime = 3000;
        int maxSleepTime = 40000;
        int countdownIncrease = 5000;

        entries.add(new NameValidator());
        entries.add(new AddressValidator());
        entries.add(new MovieValidator());
        entries.add(new FoodValidator());
//        entries.add(new BirthDateAndPlaceValidator());

        boolean allEntriesValid = true;

        int count = 0;
        for (ValidationMethod vm : entries)
        {
            boolean validInput = false;
            int rerunCount = 0;
            int sleepTime = baseSleepTime;

            while (!validInput && rerunCount < maxReruns)
            {
                validInput = entries.get(count).validate(); // Invoke the validate method
                rerunCount++;

                if (!validInput)
                {
                    if (rerunCount > 0)
                    {
                        sleepTime += countdownIncrease;
                        if (sleepTime > maxSleepTime)
                        {
                            sleepTime = maxSleepTime;
                        }
                        out.println("Input validation failed. Rerunning in ");
                        countdownTimer(sleepTime / 1000);
                        out.print(" seconds...\n");
                    }
                }
                if (validInput)
                {
                    count++;
                    break;
                }
            }

            if (!validInput) {
                out.println("Input validation failed after " + rerunCount + " reruns. Exiting...");
                allEntriesValid = false;
                break;
            }
        }
        if (allEntriesValid)
            out.println("All entries are valid.");
        return allEntriesValid;
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

}
