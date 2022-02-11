package com.pinetreesoftware.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Nutrients {
    double calories;
    double carbs;
    double fats;
    double proteins;

    public void updateRemainingNutrients(double calories, double carbs, double fats, double proteins) {
        this.setCalories(this.getCalories() - calories);
        this.setCarbs(this.getCarbs() - carbs);
        this.setFats(this.getFats() - fats);
        this.setProteins(this.getProteins() - proteins);
    }

    public Nutrients copy() {
        Nutrients nutrients = new Nutrients();
        nutrients.setCarbs(this.getCarbs());
        nutrients.setCalories(this.getCalories());
        nutrients.setFats(this.getFats());
        nutrients.setProteins(this.getProteins());
        return nutrients;
    }
}
