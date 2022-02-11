package com.pinetreesoftware.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Days {
    private List<Day> days = new ArrayList<>();

    public void addDayAndCalculateNutritionalValues(Day day, Nutrients remainingNutrients) {
        Nutrients breakFastNutrients = day.getBreakfast().getNutrients();
        Nutrients lunchNutrients = day.getLunch().getNutrients();
        Nutrients dinnerNutrients = day.getDinner().getNutrients();
        day.setCalories(
                combineNutrientTotals(
                        breakFastNutrients.getCalories(),
                        lunchNutrients.getCalories(),
                        dinnerNutrients.getCalories()));
        day.setProteins(
                combineNutrientTotals(
                        breakFastNutrients.getProteins(),
                        lunchNutrients.getProteins(),
                        dinnerNutrients.getProteins()));
        day.setCarbs(
                combineNutrientTotals(
                        breakFastNutrients.getCarbs(),
                        lunchNutrients.getCarbs(),
                        dinnerNutrients.getCarbs()));
        day.setFats(
                combineNutrientTotals(
                        breakFastNutrients.getFats(),
                        lunchNutrients.getFats(),
                        dinnerNutrients.getFats()));
        day.setRemainingCalories(remainingNutrients.getCalories());
        day.setRemainingCarbs(remainingNutrients.getCarbs());
        day.setRemainingFats(remainingNutrients.getFats());
        day.setRemainingProteins(remainingNutrients.getProteins());
        this.days.add(day);
    }

    private double combineNutrientTotals(Double nutrient1, Double nutrient2, Double nutrient3) {
        return nutrient1 + nutrient2 + nutrient3;
    }
}
