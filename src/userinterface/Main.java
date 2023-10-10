package userinterface;

import validator.NameValidator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\033[3mText goes here\033[0m");
//        NameValidator nv = new NameValidator();
//        nv.validate();
//        System.out.println(nv.getName());
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
