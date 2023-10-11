import games.minesweeper.MineSweeper;
import userinterface.UserInterface;

public class Main
{
    public static void main(String[] args)
    {
//        System.out.println("\033[3mText goes here\033[0m");
//        EntryValidator uic = new EntryValidator();
//        uic.validateEntries();
//
//        ReportGenerator reportGenerator = new ReportGenerator();
//        reportGenerator.displayReport(uic.saveDetailsToPersonObject());

//        RaceCarGame raceCarGame = new RaceCarGame();
//        raceCarGame.startRaceCarGame();

//        MineSweeper mineSweeperGame = new MineSweeper();
//        mineSweeperGame.run();

        UserInterface userInterface = new UserInterface();
        userInterface.getUserReplyAndDecideAction();
    }


}
