package logic;


public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\033[3mText goes here\033[0m");
        UserInputController uic = new UserInputController();
        uic.start();
    }
}
