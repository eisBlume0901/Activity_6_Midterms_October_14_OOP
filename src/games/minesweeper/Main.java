package games.minesweeper;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

    }
    public static void UserInfo(){
        int Max = 5;
        int DelayedSeconds = 20;
        int delayIncrement = 5;
        int currentDelay= DelayedSeconds;


        for(int attempt = 0; attempt < Max; attempt++){
            System.out.println("Input the following details");
            String fullName;
            String address;
            String course;
            String favoriteMovie;
            int favoriteNumber;
            String favoriteMovieCharacter;
            String favoriteFood;
            String birthDate;
            String birthPlace;
            int numOfChildren;

            try{
                System.out.println("Full name:");
                fullName = scan.nextLine();
                if(!isNameValid(fullName)) throw new Exception("Invalid name");
                System.out.println("Address:");
                address = scan.nextLine();
                System.out.println("Course:");
                course =scan.nextLine();
                System.out.println("Favorite movie:");
                favoriteMovie = scan.nextLine();
                System.out.println("Favorite number:");
                favoriteNumber = scan.nextInt();
                System.out.println("Favorite Movie Character:");
                favoriteMovieCharacter = scan.nextLine();
                System.out.println("Favorite food:");
                favoriteFood = scan.nextLine();
                System.out.println("Birth date");
                birthDate = scan.nextLine();
                System.out.println("Birth place:");
                birthPlace = scan.nextLine();
                System.out.println("How many children do you plan to have?");
                numOfChildren = scan.nextInt();


                // if all input checks out
                break;

            }catch(Exception e){
                System.out.println("Please try again after..." + currentDelay + " seconds");
                try{
                    Thread.sleep(currentDelay * 1000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                currentDelay += delayIncrement;
            }
        }
        System.out.println("Program ended");
        scan.close();
    }
    public static boolean isNameValid(String name){
        return name.matches("^[a-zA-Z\\s]+$");
    }
}
