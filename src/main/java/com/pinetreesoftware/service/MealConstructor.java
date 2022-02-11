package com.pinetreesoftware.service;

import com.pinetreesoftware.constants.FoodGroup;
import com.pinetreesoftware.constants.MealPlanningConstants;
import com.pinetreesoftware.model.*;
import com.pinetreesoftware.util.FoodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MealConstructor {

    public static Days constructDailyMeals(final List<Food> foods, final Nutrients nutrients) {
        System.out.println("Constructing Meals...");
        List<Food> allFoodsButProtein = new ArrayList<>();
        allFoodsButProtein.addAll(getFoodGroupFromFoods(foods, FoodGroup.CARB));
        allFoodsButProtein.addAll(getFoodGroupFromFoods(foods, FoodGroup.DAIRY));
        allFoodsButProtein.addAll(getFoodGroupFromFoods(foods, FoodGroup.VEGETABLE));
        allFoodsButProtein.addAll(getFoodGroupFromFoods(foods, FoodGroup.FRUIT));
        List<Food> proteins = getFoodGroupFromFoods(foods, FoodGroup.PROTEIN);
        Meal lunch;
        Meal dinner;
        List<Food> lunchFoods;
        List<Food> dinnerFoods;
        Days days = new Days();
        int eggIndex = 0;
        for (int i = 0; i < proteins.size(); i++) {
            dinner = new Meal();
            lunch = new Meal();
            lunchFoods = new ArrayList<>();
            dinnerFoods = new ArrayList<>();
            Day day = new Day();
            final Food protein = proteins.get(i);
            if (MealPlanningConstants.EGG.equalsIgnoreCase(protein.getName())) {
                eggIndex = i;
                continue;
            }
            Nutrients remainingNutrients = nutrients.copy();
            lunchFoods.add(protein);
            remainingNutrients.updateRemainingNutrients(protein.getCalories(), protein.getCarbs(), protein.getFat(), protein.getProtein());
            int dinnerProteinIndex = i + 1 < proteins.size() ? i + 1 : 0;
            Food dinnerProtein = proteins.get(dinnerProteinIndex);
            dinnerFoods.add(dinnerProtein);
            remainingNutrients.updateRemainingNutrients(dinnerProtein.getCalories(), dinnerProtein.getCarbs(), dinnerProtein.getFat(), dinnerProtein.getProtein());
            lunch.setFoodsAndCalculateNutritionalValues(lunchFoods);
            dinner.setFoodsAndCalculateNutritionalValues(dinnerFoods);
            buildMealsWithFoods(lunch, dinner, allFoodsButProtein, remainingNutrients);
            day.setBreakfast(BreakfastBuilder.buildBreakFast(remainingNutrients, proteins, allFoodsButProtein, eggIndex));
            day.setLunch(lunch);
            day.setDinner(dinner);
            days.addDayAndCalculateNutritionalValues(day, remainingNutrients);
        }
        return days;
    }

    private static void buildMealsWithFoods(Meal lunch,
                                           Meal dinner,
                                           List<Food> supplementalFoods,
                                           final Nutrients remainingNutrients) {
        Random randomizer = new Random();
        List<Food> lunchFoods = lunch.getFoods();
        List<Food> dinnerFoods = dinner.getFoods();
        boolean roomInMeals = true;
        int attempts = 0;
        while (roomInMeals) {
            Food randomLunchFood = supplementalFoods.get(randomizer.nextInt(supplementalFoods.size()));
            Food randomDinnerFood = supplementalFoods.get(randomizer.nextInt(supplementalFoods.size()));
            FoodUtils.insertFoodIntoMealsIfItFits(lunchFoods, lunch, randomLunchFood, remainingNutrients);
            FoodUtils.insertFoodIntoMealsIfItFits(dinnerFoods, dinner, randomDinnerFood, remainingNutrients);
            attempts++;
            if (attempts > 2) {
                roomInMeals = false;
            }
        }
        lunch.setFoodsAndCalculateNutritionalValues(lunchFoods);
        dinner.setFoodsAndCalculateNutritionalValues(dinnerFoods);
    }

    private static List<Food> getFoodGroupFromFoods(List<Food> foods, FoodGroup foodGroup) {
        List<Food> foodGroupList = new ArrayList<>();
        for (Food food : foods) {
            if (food.getFoodGroup().equals(foodGroup)) {
                foodGroupList.add(food);
            }
        }
        return foodGroupList;
    }
}
