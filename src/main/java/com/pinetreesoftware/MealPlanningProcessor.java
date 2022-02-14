package com.pinetreesoftware;

import com.google.gson.Gson;
import com.pinetreesoftware.constants.MealPlanningConstants;
import com.pinetreesoftware.model.Days;
import com.pinetreesoftware.model.Food;
import com.pinetreesoftware.model.Nutrients;
import com.pinetreesoftware.service.MealConstructor;
import com.pinetreesoftware.util.ResourceUtil;

import java.io.FileReader;
import java.util.List;

public class MealPlanningProcessor {

    public static void main(String[] args){
        System.out.println("Application Started...");
        Gson gson = new Gson();
        final List<Food> foods = ResourceUtil.getFoodsFromFileReader(ResourceUtil.getFileReaderFromInputFolder(MealPlanningConstants.FOODS_CSV));
        FileReader fileReader = ResourceUtil.getFileReaderFromInputFolder(MealPlanningConstants.NUTRIENTS_JSON);
        if (fileReader != null) {
            Nutrients nutrients = gson.fromJson(fileReader, Nutrients.class);
            final Days days = MealConstructor.constructDailyMeals(foods, nutrients);
            System.out.println("Writing days to file");
            ResourceUtil.writeDaysToFile(gson, days);
        }
    }
}
