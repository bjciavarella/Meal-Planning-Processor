package com.pinetreesoftware.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Day {
    private Meal breakfast;
    private Meal Lunch;
    private Meal Dinner;

    @SerializedName(value = "Total Calories")
    private double calories;

    @SerializedName(value = "Total Fats")
    private double fats;

    @SerializedName(value = "Total Carbs")
    private double carbs;

    @SerializedName(value = "Total Proteins")
    private double proteins;

    @SerializedName(value = "Remaining Calories")
    private double remainingCalories;

    @SerializedName(value = "Remaining Fats")
    private double remainingFats;

    @SerializedName(value = "Remaining Carbs")
    private double remainingCarbs;

    @SerializedName(value = "Remaining Proteins")
    private double remainingProteins;
}
