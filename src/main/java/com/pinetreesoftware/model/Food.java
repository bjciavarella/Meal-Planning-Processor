package com.pinetreesoftware.model;

import com.pinetreesoftware.constants.FoodGroup;
import lombok.*;
import com.opencsv.bean.CsvBindByName;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Food {

    @CsvBindByName
    private String name;

    @CsvBindByName(column = "food group")
    private FoodGroup foodGroup;

    @CsvBindByName
    private double quantity = 1;

    @CsvBindByName
    private double calories;

    @CsvBindByName
    private double protein;

    @CsvBindByName
    private double fat;

    @CsvBindByName
    private double carbs;

    public Food copyFood() {
        Food newFood = new Food();
        newFood.setName(this.getName());
        newFood.setFoodGroup(this.getFoodGroup());
        newFood.setQuantity(this.getQuantity());
        newFood.setCalories(this.getCalories());
        newFood.setProtein(this.getProtein());
        newFood.setFat(this.getFat());
        newFood.setCarbs(this.getCarbs());
        return newFood;
    }
}
