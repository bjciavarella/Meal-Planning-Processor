package com.pinetreesoftware.util;

import com.pinetreesoftware.model.Food;
import com.pinetreesoftware.model.Meal;
import com.pinetreesoftware.model.Nutrients;

import java.util.List;

public class FoodUtils {

    public static double getTotalCalories(List<Food> foods) {
        double totalCalories = 0;
        for (Food food : foods) {
            totalCalories += food.getCalories();
        }
        return totalCalories;
    }

    public static double getTotalCarbs(List<Food> foods) {
        double total = 0;
        for (Food food : foods) {
            total += food.getCarbs();
        }
        return total;
    }

    public static double getTotalFat(List<Food> foods) {
        double total = 0;
        for (Food food : foods) {
            total += food.getFat();
        }
        return total;
    }

    public static double getTotalProtein(List<Food> foods) {
        double total = 0;
        for (Food food : foods) {
            total += food.getProtein();
        }
        return total;
    }

    public static boolean doesFoodFitInMeal(Meal meal, Food food, Nutrients nutrients) {
        Nutrients mealNutrients = meal.getNutrients();
        return (mealNutrients.getCalories() + food.getCalories() <= nutrients.getCalories()) &&
                (mealNutrients.getCarbs() + food.getCarbs() <= nutrients.getCarbs()) &&
                (mealNutrients.getFats() + food.getFat() <= nutrients.getFats()) &&
                (mealNutrients.getProteins() + food.getProtein() <= nutrients.getProteins());
    }

    public static void insertFoodIntoMealsIfItFits(List<Food> foods, Meal meal, Food food, Nutrients nutrients) {
        if (doesFoodFitInMeal(meal, food, nutrients) && !isFoodAlreadyInList(foods, food)) {
            foods.add(food);
            nutrients.updateRemainingNutrients(food.getCalories(), food.getCarbs(), food.getFat(), food.getProtein());
        }
    }

    public static boolean isFoodAlreadyInList(List<Food> foods, Food food) {
        return foods.contains(food);
    }

    public static void multiplyQuantityOfFood(Food food, double quantity) {
        food.setQuantity(quantity);
        food.setCalories(food.getCalories() * quantity);
        food.setProtein(food.getProtein() * quantity);
        food.setCarbs(food.getCarbs() * quantity);
        food.setFat(food.getFat() * quantity);
    }
}
