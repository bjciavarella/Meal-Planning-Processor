package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum FoodGroup {
    PROTEIN("protein"),
    CARB("carb"),
    FRUIT("fruit"),
    DAIRY("dairy"),
    VEGATABLE("vegetable");

    private final String group;
}
