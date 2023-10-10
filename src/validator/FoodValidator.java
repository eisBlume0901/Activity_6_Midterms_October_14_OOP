package validator;

import pojo.Food;
import pojo.Person;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class FoodValidator implements ValidationMethod, InputValidator
{
    private Food food;
    private Scanner scanner = new Scanner(in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean validate() {
        out.println(ANSI_BLUE + "Food: " + ANSI_RESET);
        String foodName = scanner.nextLine();

        if (isSentenceValid(foodName))
        {
            food = new Food();
            food.setFoodName(foodName);
            setFood(food);
            return true;
        }
        return false;
    }

}
