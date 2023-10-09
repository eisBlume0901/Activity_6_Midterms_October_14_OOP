package userinterface;
import pojo.Person;
import validator.*;

import java.time.LocalDate;
import java.util.*;
import static java.lang.System.*;

public class UserInterface implements InputValidator, Searcher
{
    private Scanner scanner;
    private Person person;

    public UserInterface()
    {
        scanner = new Scanner(in);
    }
    public void start()
    {
        if (validateEntries())
        {
            displayResults();
        }
        else
        {
            out.println("Wrong entries bye bye");
        }
    }

    private List<ValidationMethod> storeValidators()
    {
        List<ValidationMethod> validators = new ArrayList<>();
        validators.add(new NameValidator());
        validators.add(new BirthDateAndPlaceValidator());
        validators.add(new AddressValidator());
        validators.add(new CourseValidator());
        validators.add(new MovieValidator());
        validators.add(new FoodValidator());
        validators.add(new NumberValidator());

        return validators;
    }
    private boolean validateEntries()
    {
        List<ValidationMethod> entries = storeValidators();
        int maxReruns = 5;
        int baseSleepTime = 15000;
        int maxSleepTime = 40000;
        int countdownIncrease = 5000;

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

    // For debugging
    private void displayResults()
    {
        person = new Person();
        out.println("Personal Information ");
        out.println("Name: " + person.getName().toString()); // String
        out.println("BirthDate: " + person.getBirthDatePlace().toString()); // String
        out.println("Age: " + calculateAge(LocalDate.of(
                person.getBirthDatePlace().getBirthYear(),
                person.getBirthDatePlace().getBirthMonth(),
                person.getBirthDatePlace().getBirthDay()))); // Integer
        out.println("Address: " +
                person.getAddress().getBarangay() + ", " +
                person.getAddress().getCity() + ", " +
                searchRegion(person.getAddress().getCity())); // String
        out.println("Course: " + person.getCourse() + ", " + searchIndustry(person.getCourse().getCourseName())); // String

        out.println("Favorites ");
        out.println("Movie: " + person.getMovie().getMovieTitle()); // String
        out.println("Movie Character: " +  person.getMovie().getMovieCharacter()); // String
        out.println("Main Character: " +
                isMainCharacter(person.getMovie().getMovieTitle(), person.getMovie().getMovieCharacter())); // String
        out.println("Food: " + person.getFood().getFoodName()); // String
        out.println("Number: " + person.getUserNumber().getFavoriteNumber());

        out.println("Preferred Number of Children: ");
        out.println("Number: " + person.getUserNumber().getPreferredNumberOfChildren());
        /*
        I MAY OVERSEE OTHER REUQIREMENTS SO PLEASE DOUBLE CHECK
        HAVE A METHOD ALREADY 1. What is the reign of the user's address? (e.g. Cavite -> Region IV-A)
        HAVE A METHOD ALREADY 2. What classification of the user's course? (e.g. IT -> Technology)
        3. How many consonants and vowels in the user's full name?
        4. How many words in the user's full name?
        5. Check if the user's age is Senior, Mid, Teenager, Child or Baby
        6. Check what is the zodiac sign of the user
        HAVE A METHOD ALREADY 7. Identify if the user's fav. movie is action, comedy, or thriller
        HAVE A METHOD ALREADY 8. Identify if the user's fav. movie character is the main character or supporting character.
        9. Get the binary, octal & hexadecimal value of the user's  fav. number
        10. Provide user's own psychological feedback based on the preferred number of children by the user.
         */
    }
}
