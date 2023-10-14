package logic;
import pojo.Person;
import logic.validator.*;
import java.util.*;
import static java.lang.System.*;

public class EntryValidator implements InputValidator, Searcher
{
    private Person person;
    private NameValidator nameValidator;
    private BirthDateAndPlaceValidator birthDateAndPlaceValidator;
    private AddressValidator addressValidator;
    private CourseValidator courseValidator;
    private MovieValidator movieValidator;
    private FoodValidator foodValidator;
    private NumberValidator numberValidator;
    public EntryValidator()
    {
        person = new Person();
        nameValidator = new NameValidator();
        birthDateAndPlaceValidator = new BirthDateAndPlaceValidator();
        addressValidator = new AddressValidator();
        courseValidator = new CourseValidator();
        movieValidator = new MovieValidator();
        foodValidator = new FoodValidator();
        numberValidator = new NumberValidator();
    }

    public void validateEntries()
    {
//        out.println("\033[m" + """
//                Personal Details
//                First name: Maria Isabel Antonia
//                Middle name:  Santos
//                Last name: Cruz
//                Birth month: January
//                Birth day: 25
//                Birth year: 2002
//                Birth place: Cavite
//                Barangay: San Antonio
//                Course: Computer Science
//                City: Makati
//
//                Favorites
//                Movie title: The Lord of the Rings: The Fellowship of the Ring
//                Movie character: Frodo Baggins
//                Food: Potato salad
//                Number: 7
//
//                Preferred Number of Children: 3
//                """ + "\033[0m");

        List<ValidationMethod> validators = new ArrayList<>();
        validators.add(nameValidator);
        validators.add(birthDateAndPlaceValidator);
        validators.add(addressValidator);
        validators.add(courseValidator);
        validators.add(movieValidator);
        validators.add(foodValidator);
        validators.add(numberValidator);

        int baseSleepTime = 20000;
        int nextBaseSleepTime = 40000;
        int countdownIncrease = 5000;

        int count = 0;
        for (ValidationMethod vm : validators)
        {
            boolean validInput = false;
            int rerunCount = 0;
            int sleepTime = baseSleepTime;

            while (!validInput)
            {
                validInput = validators.get(count).validate();
                rerunCount++;

                if (validInput)
                {
                    count++;
                    break;
                }
                else
                {
                    if (rerunCount > 0)
                    {
                        sleepTime += countdownIncrease;

                        if (sleepTime == nextBaseSleepTime)
                        {
                            sleepTime = nextBaseSleepTime;
                            sleepTime += countdownIncrease;
                        }
                        err.println("Input validation failed. Rerunning in ...");
                        countdownTimer(sleepTime / 1000);
                    }
                }
            }

        }
    }

    private void countdownTimer(int seconds)
    {
        for (int i = seconds; i >= 1; i--)
        {
            err.print(i + " ");
            sleep(1000);

            if (i == 1)
            {
                err.print("seconds");
            }
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

    public Person saveDetailsToPersonObject()
    {
        person.setName(nameValidator.getName());
        person.setBirthDatePlace(birthDateAndPlaceValidator.getBirthDateAndPlace());
        person.setAddress(addressValidator.getAddress());
        person.setCourse(courseValidator.getCourse());
        person.setMovie(movieValidator.getMovie());
        person.setFood(foodValidator.getFood());
        person.setUserNumber(numberValidator.getUserNumber());

        return person;
    }
}
