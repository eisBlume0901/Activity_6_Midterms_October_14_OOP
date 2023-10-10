package validator;
import java.util.*;
import static java.lang.System.*;

public class Test implements InputValidator, Searcher {
    static Scanner scanner = new Scanner(System.in);
    static Test t = new Test();
    public static void main(String[] args) {
        String martha = "martha";
        out.println(martha.substring(0,1).toUpperCase() + martha.substring(1).toLowerCase());
        out.println(t.allCharactersFromAMovie("Batman Begins"));
        out.println(t.isMovieCharacter("Batman Begins", "Alfred"));
        out.println(t.isMainCharacter("Batman Begins", "Batman"));
        out.println(t.getGenre(" Sen to Chihiro no Kamikakushi"));
        out.println(t.searchIndustry("computer science"));
//        out.println(t.getCharactersForMovie("Batman Begins"));
//        out.println(t.getSupportingCharacters("Batman Begins"));
//        out.println(t.isStringInputValid("179-L"));
//        out.println(t.isEnglishWord("Mary"));
//        out.println(t.isEnglishWord("Kate"));
//        out.println(t.isPhraseValid("Mary Kate"));
//        out.println(t.isSentenceValid("Mary Kate"));
//
//
//        List<ValidationMethod> entries = new ArrayList<>();
//        int maxReruns = 5;
//        int baseSleepTime = 3000;
//        int maxSleepTime = 40000;
//        int countdownIncrease = 5000;
//
//        entries.add(new Namealidator());
//        entries.add(new AddressValidator());
//        entries.add(new MovieValidator());
//        // Add more entries as needed
//
//        boolean allEntriesValid = true;
//
//        int count = 0;
//        for (ValidationMethod vm : entries)
//        {
//            boolean validInput = false;
//            int rerunCount = 0;
//            int sleepTime = baseSleepTime;
//
//            while (!validInput && rerunCount < maxReruns)
//            {
//                validInput = entries.get(count).validate(); // Invoke the validate method
//                rerunCount++;
//
//                if (!validInput)
//                {
//                    if (rerunCount > 0)
//                    {
//                        sleepTime += countdownIncrease;
//                        if (sleepTime > maxSleepTime)
//                        {
//                            sleepTime = maxSleepTime;
//                        }
//                        out.println("Entry: " + count + "\nInput validation failed. Rerunning in");
//                        countdownTimer(sleepTime / 1000);
//                        out.print(" seconds...\n");
//                    }
//                }
//                if (validInput)
//                {
//                    count++;
//                    break;
//                }
//            }
//
//            if (!validInput) {
//                out.println("Entry " + count + "\nInput validation failed after " + rerunCount + " reruns. Exiting...");
//                allEntriesValid = false;
//                break;
//            }
//        }
//        if (allEntriesValid)
//            out.println("All entries are valid.");
    }

    public interface ValidationMethod {
        boolean validate();
    }

    // Implement separate classes for each validation method
    static class NameValidator implements ValidationMethod {
        public boolean validate() {
            out.println("First name: ");
            String firstName = scanner.nextLine();
            out.println("Middle name: ");
            String middleName = scanner.nextLine();
            out.println("Last name: ");
            String lastName = scanner.nextLine();

            if ( (t.isStringInputValid(firstName) && t.isEnglishWord(firstName)) &&
                    t.isStringInputValid(middleName) &&
                    t.isEnglishWord(lastName) )
            {
                // set to Name class
                out.println("True all valid");
                return true;
            }
            return false;
        }
    }

    static class AddressValidator implements ValidationMethod {
        public boolean validate() {
            out.println("Barangay: ");
            String barangayName = scanner.nextLine();
            out.println("City: ");
            String cityName = scanner.nextLine();

            if (t.isStringInputValid(barangayName) && t.cityExists(cityName))
            {
                // set to Address class
                // should also search the region
                return true;
            }
            return false;
        }
    }

    static class MovieValidator implements ValidationMethod {
        public boolean validate() {
            // Favorites sector
            out.println("Movie title: ");
            String movieName = scanner.nextLine();
            out.println("Movie character: ");
            String movieCharacter = scanner.nextLine();

            if (t.isSentenceValid(movieName) && t.isSentenceValid(movieCharacter))
            {
                // set to Movie class
                return true;
            }
            return false;
        }
    }

    // Add more validator classes as needed

    private static void countdownTimer(int seconds) {
        for (int i = seconds; i >= 1; i--) {
            out.print(i + " ");
            sleep(1000);
        }
        out.println();
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

//
//import userinterface.UserInterface;
//
//import java.util.*;
//
//import static java.lang.System.*;
//
//public class Test implements InputValidator, Searcher
//{
//    static Scanner scanner = new Scanner(in);
//    static Test t =  new Test();
//    public static void main(String[] args)
//    {
//
//
////        out.println(t.isStringInputValid("kate"));
////        out.println(t.isEnglishWord("Kate"));
////        out.println();
////        out.println(t.isStringInputValid("chloe"));
////        out.println(t.isEnglishWord("Chloe"));
////        out.println();
////        out.println(t.isStringInputValid("aaron"));
////        out.println(t.isEnglishWord("aaron"));
////        out.println();
////        out.println(t.isStringInputValid("blueberry"));
////        out.println(t.isEnglishWord("blueberry"));
////        out.println();
////        out.println(t.isStringInputValid("fkajkafjakfjakf"));
////        out.println(t.isEnglishWord("fkajkafjakfjakf"));
////        out.println();
////        out.println(t.isEnglishWord("anecito"));
////        out.println(t.isEnglishWord("Sophisticated"));
////        out.println(t.searchRegion("Makati"));
////        out.println(t.searchIndustry("Information Technology"));
////        out.println(t.isSentenceValid("The Mischievous"));
////        out.println(t.isEnglishWord("you"));
////        out.println(t.isSentenceValid("Mango sandwich"));
//        out.println(t.cityExists("Makati"));
//
//
//        List<Boolean> entries = new ArrayList<>();
//        int maxReruns = 5;
//        int baseSleepTime = 3000; // 15000 originally, 3000 is for testing purposes only
//        int maxSleepTime = 40000;
//        int countdownIncrease = 5000;
//
//        entries.add(isNameValid());
//        entries.add(isAddressValid());
////        entries.add(isMovieValid());
//
//        boolean allEntriesValid = true;
//
//        for (int i = 0; i < entries.size(); i++)
//        {
//            boolean validInput = false;
//            int rerunCount = 0;
//            int sleepTime = baseSleepTime;
//
//            while (!validInput && rerunCount < maxReruns)
//            {
//                if (rerunCount > 0)
//                {
//                    sleepTime += countdownIncrease;
//                    if (sleepTime > maxSleepTime)
//                    {
//                        sleepTime = maxSleepTime;
//                    }
//                    out.println("Entry: " + i + "\nInput validation failed. Rerunning in");
//                    countdownTimer(sleepTime / 1000);
//                    out.print(" seconds...\n");
//                }
//
//                validInput = entries.get(i);
//                rerunCount++;
//            }
//
//            if (!validInput)
//            {
//                out.println("Entry " + i + "\nInput validation failed after " + rerunCount + " reruns. Exiting...");
//                allEntriesValid = false;
//                break;
//            }
//        }
//        if (allEntriesValid)
//            out.println("All entries are valid.");
//    }
//
//    public static boolean isNameValid()
//    {
//        out.println("First name: ");
//        String firstName = scanner.nextLine();
//        out.println("Middle name: ");
//        String middleName = scanner.nextLine();
//        out.println("Last name: ");
//        String lastName = scanner.nextLine();
//
//        if ( (t.isStringInputValid(firstName) && t.isEnglishWord(firstName)) &&
//                t.isStringInputValid(middleName) &&
//                t.isEnglishWord(lastName) )
//        {
//            // set to Name class
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isAddressValid()
//    {
//        out.println("Barangay: ");
//        String barangayName = scanner.nextLine();
//        out.println("City: ");
//        String cityName = scanner.nextLine();
//
//        if (t.isStringInputValid(barangayName) && t.cityExists(cityName))
//        {
//            // set to Address class
//            // should also search the region
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isMovieValid()
//    {
//        // Favorites sector
//        out.println("Movie title: ");
//        String movieName = scanner.nextLine();
//        out.println("Movie character: ");
//        String movieCharacter = scanner.nextLine();
//
//        if (t.isSentenceValid(movieName) && t.isSentenceValid(movieCharacter))
//        {
//            // set to Movie class
//            return true;
//        }
//        return false;
//    }
//
//    private static void countdownTimer(int seconds)
//    {
//        for (int i = seconds; i >= 1; i--)
//        {
//            out.print(i + " ");
//            sleep(1000);
//        }
//        out.println();
//    }
//
//    private static void sleep(int milliseconds)
//    {
//        try
//        {
//            Thread.sleep(milliseconds);
//        }
//        catch (InterruptedException ie)
//        {
//            ie.printStackTrace();
//        }
//    }
//
//
//}
//
//
