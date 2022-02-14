package com.pinetreesoftware.model;

import com.pinetreesoftware.util.FoodUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Meal {
    private List<Food> foods;
    private Nutrients nutrients = new Nutrients();

    public void setFoodsAndCalculateNutritionalValues(List<Food> foods) {
        setFoods(foods);
        Nutrients nutrients = new Nutrients();
        nutrients.setCalories(FoodUtils.getTotalCalories(this.foods));
        nutrients.setCarbs(FoodUtils.getTotalCarbs(this.foods));
        nutrients.setFats(FoodUtils.getTotalFat(this.foods));
        nutrients.setProteins(FoodUtils.getTotalProtein(this.foods));
        this.setNutrients(nutrients);
    }
}
