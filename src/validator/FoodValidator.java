package validator;

import pojo.Food;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class FoodValidator implements ValidationMethod, InputValidator
{
    private Food food;
    private Scanner scanner = new Scanner(in);

    @Override
    public boolean validate() {
        // Favorites sector
        out.println("Food: ");
        String foodName = scanner.nextLine();

        if (isSentenceValid(foodName))
        {
            food = new Food();
            food.setFoodName(foodName);
            return true;
        }
        return false;
    }
}
