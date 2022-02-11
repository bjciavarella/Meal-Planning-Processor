package com.pinetreesoftware.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum FoodGroup {
    PROTEIN("protein"),
    SHAKE("shake"),
    CARB("carb"),
    FRUIT("fruit"),
    DAIRY("dairy"),
    VEGETABLE("vegetable");

    private final String group;
}
