package userinterface;

import logic.UserInputController;

public class UserInterface
{
    private UserInputController userInputController;

    public UserInterface()
    {
        userInputController = new UserInputController();
    }

    public UserInputController getUserInputController() {
        return userInputController;
    }
}
