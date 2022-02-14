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
        List<Food> carbs = getFoodGroupFromFoods(foods, FoodGroup.CARB);
        List<Food> dairy = getFoodGroupFromFoods(foods, FoodGroup.DAIRY);
        List<Food> vegetable = getFoodGroupFromFoods(foods, FoodGroup.VEGETABLE);
        List<Food> fruit = getFoodGroupFromFoods(foods, FoodGroup.FRUIT);
        List<Food> snack = getFoodGroupFromFoods(foods, FoodGroup.SNACK);
        List<Food> proteins = getFoodGroupFromFoods(foods, FoodGroup.PROTEIN);
        Meal breakfast;
        Meal lunch;
        Meal dinner;
        List<Food> breakfastFoods;
        List<Food> lunchFoods;
        List<Food> dinnerFoods;
        Days days = new Days();
        Day day;
        Food egg = new Food();
        Food shake = new Food();
        int eggIndex = -1;
        int shakeIndex = -1;
        int index = 0;
        for (Food protein : proteins) {
            //Getting egg and shake proteins since we will be added them to every day no matter what
            if (MealPlanningConstants.EGG.equalsIgnoreCase(protein.getName())) {
                egg = protein.copyFood();
                eggIndex = index;
            }
            if (protein.getName().toLowerCase().contains(MealPlanningConstants.SHAKE)) {
                shake = protein.copyFood();
                shakeIndex = index;
            }
            index++;
        }
        if (eggIndex != -1) {
            proteins.remove(eggIndex);
            shakeIndex--;
        }
        if (shakeIndex != -1) {
            proteins.remove(shakeIndex);
        }
        for (int i = 0; i < proteins.size(); i++) {
            if (i + 1 >= proteins.size()) {
                continue;
            }
            breakfast = new Meal();
            breakfastFoods = new ArrayList<>();
            lunchFoods = new ArrayList<>();
            dinnerFoods = new ArrayList<>();
            Nutrients remainingNutrients = nutrients.copy();
            final Food protein = proteins.get(i);
            FoodUtils.insertFoodIntoFoodsIfItFits(breakfastFoods, breakfast, egg, remainingNutrients);
            FoodUtils.insertFoodIntoFoodsIfItFits(breakfastFoods, breakfast, shake, remainingNutrients);
            breakfast.setFoodsAndCalculateNutritionalValues(breakfastFoods);
            remainingNutrients.addFoodToFoodListAndUpdateRemainingNutrients(lunchFoods, protein);
            for (int j = i + 1; j < proteins.size(); j++) {
                Food protein2 = proteins.get(j);
                remainingNutrients.addFoodToFoodListAndUpdateRemainingNutrients(dinnerFoods, protein2);
                for (int k = 0; k < carbs.size(); k++) {
                    if (k + 1 >= carbs.size()) {
                        continue;
                    }
                    Food carb = carbs.get(i);
                    for (int l = k + 1; l < carbs.size(); l++) {
                        Nutrients dailyNutrients = remainingNutrients.copy();
                        Food carb2 = carbs.get(l);
                        lunch = new Meal();
                        dinner = new Meal();
                        List<Food> newDinnerFoods = FoodUtils.copyFoods(dinnerFoods);
                        List<Food> newLunchFoods = FoodUtils.copyFoods(lunchFoods);
                        FoodUtils.insertFoodIntoFoodsIfItFits(newLunchFoods, lunch, carb, dailyNutrients);
                        FoodUtils.insertFoodIntoFoodsIfItFits(newDinnerFoods, dinner, carb2, dailyNutrients);
                        lunch.setFoodsAndCalculateNutritionalValues(newLunchFoods);
                        dinner.setFoodsAndCalculateNutritionalValues(newDinnerFoods);

                        day = new Day();
                        day.setBreakfast(breakfast);
                        day.setLunch(lunch);
                        day.setDinner(dinner);
                        days.addDayAndCalculateNutritionalValues(day, dailyNutrients);
                    }
                }

            }
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
            FoodUtils.insertFoodIntoFoodsIfItFits(lunchFoods, lunch, randomLunchFood, remainingNutrients);
            FoodUtils.insertFoodIntoFoodsIfItFits(dinnerFoods, dinner, randomDinnerFood, remainingNutrients);
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
