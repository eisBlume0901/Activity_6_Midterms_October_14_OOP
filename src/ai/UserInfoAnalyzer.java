package ai;

import java.time.MonthDay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInfoAnalyzer {

    public static void main(String[] args) {


        System.out.println(countWords("Mary Kate Franco Anecito"));
        Scanner scanner = new Scanner(System.in);

        StringBuilder report = new StringBuilder();

        // Task 1: Count consonants and vowels in the user's full name
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        int consonantCount = countConsonants(fullName);
        int vowelCount = countVowels(fullName);
        report.append("Comprehensive Report:\n");
        report.append("    ").append(fullName).append(", whose name has ").append(consonantCount)
                .append(" consonants, ").append(vowelCount).append(" vowels,");

        // Task 2: Count words in the user's full name
        int wordCount = countWords(fullName);
        report.append(" and has ").append(wordCount).append(" words");

        // Task 3: Determine the age category
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        String ageCategory = determineAgeCategory(age);
        report.append(", is ").append(age).append(" years old, and falls into the ").append(ageCategory).append(" category. ");

        // Task 4: Get the binary, octal, and hexadecimal value of the user's favorite number
        System.out.print("Enter your favorite number: ");
        int favoriteNumber = scanner.nextInt();
        String binaryValue = toBinary(favoriteNumber);
        String octalValue = toOctal(favoriteNumber);
        String hexadecimalValue = toHexadecimal(favoriteNumber);
        report.append(fullName).append("'s favorite number is ").append(favoriteNumber).append(", which has a binary value of ").append(binaryValue).append(", an octal value of ").append(octalValue).append(", and a hexadecimal value of ").append(hexadecimalValue).append(". ");

        // Task 5: Determine the Zodiac sign based on birthdate
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your birthdate (MM-DD): ");
        String input = scanner.nextLine();
        MonthDay birthdate = parseBirthdate(input);
        String zodiacSign = findZodiacSign(birthdate);

        report.append(fullName).append("'s birth date is on ").append(birthdate);
        if (zodiacSign != null) {
            report.append(", which makes their Zodiac sign a(n) ").append(zodiacSign).append(". ");
        } else {
            report.append("(Zodiac Sign: Invalid birthdate or not found.)");
        }

        // Task 6: Get user's preferred number of children and provide psychological feedback
        System.out.print("Enter your preferred number of children: ");
        int numberOfChildren = scanner.nextInt();
        providePsychologicalFeedback(report, numberOfChildren);

        scanner.close();

        // Count the letters in the comprehensive report
        int reportLetterCount = countLettersInText(report.toString());

        // Insert a new line every 300 letters in the report
        int lettersPerLine = 300;
        for (int i = lettersPerLine; i < reportLetterCount; i += lettersPerLine) {
            report.insert(i, "\n");
            reportLetterCount++;
        }

        // Display the comprehensive report with proper formatting
        String formattedReport = formatParagraph(report.toString());
        System.out.println(formattedReport);
    }

    // Task 1: Count consonants and vowels in a string
    // finn
    public static int countConsonants(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isConsonant(ch)) {
                count++;
            }
        }
        return count;
    }

    // finn
    public static int countVowels(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isVowel(ch)) {
                count++;
            }
        }
        return count;
    }

    // finn
    public static boolean isConsonant(char ch) {
        return Character.isLetter(ch) && !isVowel(ch);
    }

    // finn
    public static boolean isVowel(char ch) {
        char lowerCh = Character.toLowerCase(ch);
        return lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u';
    }

    // Task 2: Count words in a string using regular expression
    // finn
    public static int countWords(String str) {
        List<String> words = List.of(str.split("\\s+"));
        return words.size();
    }

    // Task 3: Determine age category
    // finn
    public static String determineAgeCategory(int age) {
        if (age <= 4) {
            return "Baby";
        } else if (age >= 5 && age <= 12) {
            return "Child";
        } else if (age >= 13 && age <= 17) {
            return "Teenager";
        } else if (age >= 18 && age <= 65) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    // Task 4: Convert an integer to binary, octal, and hexadecimal
    // finn
    public static String toBinary(int number) {
        return Integer.toBinaryString(number);
    }
    // finn

    public static String toOctal(int number) {
        return Integer.toOctalString(number);
    }
    // finn

    // finn
    public static String toHexadecimal(int number) {
        return Integer.toHexString(number);
    }

    // Task 5: Create a map that associates MonthDay with Zodiac signs
    private static Map<MonthDay, String> createZodiacMap() {
        Map<MonthDay, String> zodiacMap = new HashMap<>();
        zodiacMap.put(MonthDay.of(3, 21), "Aries");
        zodiacMap.put(MonthDay.of(4, 20), "Taurus");
        zodiacMap.put(MonthDay.of(5, 21), "Gemini");
        zodiacMap.put(MonthDay.of(6, 21), "Cancer");
        zodiacMap.put(MonthDay.of(7, 23), "Leo");
        zodiacMap.put(MonthDay.of(8, 23), "Virgo");
        zodiacMap.put(MonthDay.of(9, 23), "Libra");
        zodiacMap.put(MonthDay.of(10, 23), "Scorpio");
        zodiacMap.put(MonthDay.of(11, 22), "Sagittarius");
        zodiacMap.put(MonthDay.of(12, 22), "Capricorn");
        zodiacMap.put(MonthDay.of(1, 20), "Aquarius");
        zodiacMap.put(MonthDay.of(2, 19), "Pisces");
        return zodiacMap;
    }

    // Task 5: Parse the user input into a MonthDay
    private static MonthDay parseBirthdate(String input) {
        String[] parts = input.split("-");
        if (parts.length == 2) {
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            return MonthDay.of(month, day);
        }
        return null;
    }

    // finn

    // Task 5: Find the Zodiac sign based on the birthdate
    private static String findZodiacSign(MonthDay birthdate) {
        Map<MonthDay, String> zodiacMap = createZodiacMap();
        for (Map.Entry<MonthDay, String> entry : zodiacMap.entrySet()) {
            if (birthdate.equals(entry.getKey()) || (birthdate.isAfter(entry.getKey()) && birthdate.isBefore(nextMonthDay(entry.getKey())))) {
                return entry.getValue();
            }
        }
        return null;
    }

    // Task 5: Helper function to get the next MonthDay (for the end date of a Zodiac sign)
    private static MonthDay nextMonthDay(MonthDay monthDay) {
        int month = monthDay.getMonthValue();
        int day = monthDay.getDayOfMonth();
        if (month == 12 && day == 22) {
            return MonthDay.of(1, 19); // Wrap around to January for Capricorn
        } else {
            return MonthDay.of(month + 1, day);
        }
    }

    // Task 6: Provide psychological feedback based on the number of children
    // finn
    private static void providePsychologicalFeedback(StringBuilder report, int numberOfChildren) {
        report.append("Their preferred number of children is ").append(numberOfChildren).append(". ");
        String feedback;
        if (numberOfChildren == 0) {
            feedback = "Choosing not to have children is a valid and personal choice. It's important to emphasize that this choice, like any other related to family planning, is deeply personal and multifaceted, influenced by a complex interplay of psychological factors. First, individuals who opt not to have children may highly value their personal autonomy and independence. They may prioritize the freedom to make choices without the responsibilities and commitments that come with parenthood. This suggests a strong sense of self and a desire for self-fulfillment. Second, some individuals may choose to forgo parenthood due to concerns about the environment. They may be deeply committed to sustainability and believe that reducing the global population is an ethical choice in the face of ecological challenges. Third, a desire to focus on one's career, personal growth, or self-actualization can also be a driving force behind this decision. These individuals may have ambitious goals and view parenthood as potentially conflicting with their professional or personal aspirations. Finally, economic factors may play a significant role. A person may choose not to have children due to concerns about the cost of raising them, wanting to maintain financial stability, or wanting to enjoy a certain lifestyle. In conclusion, the decision to have no children is a deeply personal one, influenced by a myriad of psychological factors. ";
        } else if (numberOfChildren >= 1 && numberOfChildren <= 3) {
            feedback = "A person expressing a desire to have one to three children presents a range of psychological motivations and considerations in their family planning decisions. Such preferences can be influenced by a combination of personal values, life experiences, and individual circumstances. Firstly, these individuals often prioritize a balanced approach to family life. They may see the benefits of having a moderate number of children, believing it allows them to maintain a manageable level of responsibility while still experiencing the joys and rewards of parenthood. This suggests a desire for a fulfilling family life without feeling overwhelmed. Secondly, the desire for a small to medium-sized family may stem from a strong focus on quality over quantity. These individuals may want to provide their children with ample attention, resources, and opportunities for personal growth. They recognize that having fewer children can allow for more personalized parenting and deeper connections with each child. Thirdly, psychological factors such as attachment style and childhood experiences can play a role in this decision. Those who experienced secure and nurturing attachments in their own upbringing may be more inclined to want to replicate these positive experiences in their own families. Conversely, individuals who had less positive or stable childhoods may choose a smaller family size to ensure a more secure and supportive environment for their children. Additionally, the consideration of economic stability often factors into this choice. Those who desire one to three children may want to ensure that they can provide for their children's material needs and offer them opportunities for education and extracurricular activities without undue financial strain.";
        } else {
            feedback = "Having more than 4 children is a significant responsibility and may require careful planning. This choice represents a strong commitment to a larger family size and can be influenced by various factors: Firstly, individuals who aspire to have more than four children often place a high value on the concept of a large, close-knit family. They may have grown up in similar family structures or witnessed the benefits of having numerous siblings, fostering a desire to replicate these experiences for their own offspring. This suggests a deep appreciation for the emotional support and camaraderie that can come from a large family. Secondly, religious or cultural beliefs may play a significant role in this decision. Some cultures and religions encourage larger families as a means of preserving traditions, passing down values, or fulfilling religious obligations. These individuals may view having many children as a form of spiritual fulfillment or cultural continuity. Thirdly, a desire to leave a lasting legacy could be a motivating factor. Those who wish to have more than four children may believe that expanding their family lineage is a way to make a meaningful and lasting impact on the world, ensuring their values and genes are carried forward through generations. Furthermore, personality traits such as extraversion and sociability may drive this choice. Individuals who thrive in social settings and enjoy the dynamics of a bustling household may naturally gravitate toward larger families. Economic considerations also come into play. While it may seem challenging to support a larger family, some individuals who desire more than four children may have devised financial plans and strategies to ensure their children's well-being, education, and opportunities.";
        }
        report.append(feedback);
    }

    // Count letters in a string including feedback
    // finn
    public static int countLettersInText(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                count++;
            }
        }
        return count;
    }

    // Format a paragraph with proper indentation and line breaks
    private static String formatParagraph(String paragraph) {
        StringBuilder formattedText = new StringBuilder();
        String[] lines = paragraph.split("\n");
        int currentLineLength = 0;
        for (String line : lines) {
            if (currentLineLength + line.length() > 300) {
                formattedText.append("\n");
                currentLineLength = 0;
            }
            formattedText.append(line).append("\n");
            currentLineLength += line.length();
        }
        return formattedText.toString();
    }
}
