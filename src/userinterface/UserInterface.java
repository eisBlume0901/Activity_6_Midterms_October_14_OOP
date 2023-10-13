package userinterface;

import games.minesweeper.MineSweeperGame;
import games.racecar.Game;
import logic.EntryValidator;
import logic.userInfoProcessorAndAnalyzer.ReportGenerator;
import logic.userInfoProcessorAndAnalyzer.StringProcessor;

import java.util.Scanner;

public class UserInterface
{
    private StringProcessor stringProcessor;
    private Game raceCarGame;
    private MineSweeperGame mineSweeperGame;
    private ReportGenerator reportGenerator;
    private EntryValidator entryValidator;
    private Scanner scanner;

    public UserInterface()
    {
        stringProcessor = new StringProcessor();
        reportGenerator = new ReportGenerator();
        entryValidator = new EntryValidator();
        scanner = new Scanner(System.in);
    }
    public void start() {
        // Ask the user for a reply
        entryValidator.validateEntries();
        reportGenerator.displayReport(entryValidator.saveDetailsToPersonObject());

        System.out.print("Please provide your reply: ");
        String userReply = scanner.nextLine();

        // Check if the reply has more than 30 words
        if (stringProcessor.countWords(userReply) > 30) {
            System.out.print("Your reply is quite long. Do you want to play a game (yes/no)? ");
            String playGame = scanner.nextLine().toLowerCase();
            if ("yes".equals(playGame)) {
                System.out.println("Redirecting you to play a 1 vs 1 race car game. Enjoy!");
                // TODO: Redirects user to race car game :D
                raceCarGame = new Game();
                raceCarGame.run();
            } else {
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0); // Exit the program
            }
        } else if (stringProcessor.countWords(userReply) == 30) {
            System.out.println("Your reply has 30 words. Exiting the program. Goodbye!");
            System.exit(0); // Exit the program
        } else {
            System.out.println("Your reply is less than 30 words. Redirecting you to play a minesweeper game. Enjoy!");
            // TODO: Insert minesweeper heeere
            mineSweeperGame = new MineSweeperGame();
            mineSweeperGame.run();
        }
    }
}
