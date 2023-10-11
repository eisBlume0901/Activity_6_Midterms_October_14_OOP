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
        out.println("\033[m" + """
                Personal Details
                First name: Maria Isabel Antonia
                Middle name:  Santos
                Last name: Cruz 
                Birth month: January
                Birth day: 25
                Birth year: 2002
                Birth place: Cavite
                Barangay: San Antonio
                Course: Computer Science
                City: Makati
                
                Favorites
                Movie title: The Lord of the Rings: The Fellowship of the Ring
                Movie character: Frodo Baggins
                Food: Potato salad
                Number: 7
                
                Preferred Number of Children: 3
                """ + "\033[0m");

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

//    private void displayResults()
//    {
//        saveDetailsToPersonObject();
//        out.println("Personal Information ");
//        out.println("Name: " + person.getName().toString()); // String
//        out.println("Number of consonants: " + stringProcessor.countConsonants(person.getName().toString()));
//        out.println("Number of vowels: " + stringProcessor.countVowels(person.getName().toString()));
//        out.println("Number of words: " + stringProcessor.countWords(person.getName().toString()));
//
//        out.println("Birth date: " + person.getBirthDatePlace().toString()); // String
//
//        Month birthMonth = person.getBirthDatePlace().getBirthMonth();
//        MonthDay birthMonthDay = MonthDay.of(birthMonth, person.getBirthDatePlace().getBirthDay());
//
//        out.println("Zodiac sign: " + userInfoAnalyzer.findZodiacSign(birthMonthDay));
//
//        int age = calculateAge(LocalDate.of(
//                person.getBirthDatePlace().getBirthYear(),
//                person.getBirthDatePlace().getBirthMonth(),
//                person.getBirthDatePlace().getBirthDay()));
//        out.println("Age: " + age); // Integer
//        out.println("Age Category: " + userInfoAnalyzer.determineAgeCategory(age));
//        out.println("Address: " +
//                person.getAddress().getBarangay() + ", " +
//                person.getAddress().getCity() + ", " +
//                searchRegion(person.getAddress().getCity())); // String
//        out.println("Course: " + person.getCourse().getCourseName() + ", " + searchIndustry(person.getCourse().getCourseName())); // String
//
//        out.println("Favorites ");
//        out.println("Movie: " + person.getMovie().getMovieTitle()); // String
//
//        List<String> genreList = getGenre(person.getMovie().getMovieTitle());
//        String genres = genreList.toString().substring(1, genreList.toString().length() - 1);
//        out.println("Movie Genre: " + genres);
//
//        out.println("Movie Character: " +  person.getMovie().getMovieCharacter()); // String
//        out.println("Main Character: " +
//                isMainCharacter(person.getMovie().getMovieTitle(), person.getMovie().getMovieCharacter())); // String
//        out.println("Food: " + person.getFood().getFoodName()); // String
//        out.println("Number: " + person.getUserNumber().getFavoriteNumber());
//
//        out.println("Binary form: " + stringProcessor.toBinary(person.getUserNumber().getFavoriteNumber()));
//        out.println("Hexadecimal form: " + stringProcessor.toHexadecimal(person.getUserNumber().getFavoriteNumber()));
//        out.println("Octal form: " + stringProcessor.toOctal(person.getUserNumber().getFavoriteNumber()));
//
//        out.println("Preferred Number of Children: ");
//
//        StringBuilder report = new StringBuilder();
//        userInfoAnalyzer.providePsychologicalFeedback(report, person.getUserNumber().getPreferredNumberOfChildren());
////        /*
////        I MAY OVERSEE OTHER REUQIREMENTS SO PLEASE DOUBLE CHECK
////        HAVE A METHOD ALREADY 1. What is the reign of the user's address? (e.g. Cavite -> Region IV-A)
////        HAVE A METHOD ALREADY 2. What classification of the user's course? (e.g. IT -> Technology)
////        3. How many consonants and vowels in the user's full name?
////        4. How many words in the user's full name?
////        5. Check if the user's age is Senior, Mid, Teenager, Child or Baby
////        6. Check what is the zodiac sign of the user
////        HAVE A METHOD ALREADY 7. Identify if the user's fav. movie is action, comedy, or thriller
////        HAVE A METHOD ALREADY 8. Identify if the user's fav. movie character is the main character or supporting character.
////        9. Get the binary, octal & hexadecimal value of the user's  fav. number
////        10. Provide user's own psychological feedback based on the preferred number of children by the user.
////         */
//    }
}
