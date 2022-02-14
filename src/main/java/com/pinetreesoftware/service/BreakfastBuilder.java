package com.pinetreesoftware.service;

import com.pinetreesoftware.model.Food;
import com.pinetreesoftware.model.Meal;
import com.pinetreesoftware.model.Nutrients;
import com.pinetreesoftware.util.FoodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BreakfastBuilder {

    public static Meal buildBreakFast(Nutrients nutrients, List<Food> proteins, List<Food> allFoodButProteins, int eggsIndex) {
        System.out.println("Building Breakfast");
        Meal breakFast = new Meal();
        List<Food> foods = new ArrayList<>();
        Food egg = proteins.get(eggsIndex).copyFood();
        FoodUtils.multiplyQuantityOfFood(egg, 4);
        FoodUtils.insertFoodIntoFoodsIfItFits(foods, breakFast, egg, nutrients);
        Random randomizer = new Random();
        Food randomFood = allFoodButProteins.get(randomizer.nextInt(allFoodButProteins.size()));
        int attempts = 0;
        while (attempts < 5) {
            FoodUtils.insertFoodIntoFoodsIfItFits(foods, breakFast, randomFood, nutrients);
            attempts++;
            randomFood = allFoodButProteins.get(randomizer.nextInt(allFoodButProteins.size()));
        }

        breakFast.setFoodsAndCalculateNutritionalValues(foods);
        return breakFast;
    }
}
