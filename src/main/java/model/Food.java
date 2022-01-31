package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.opencsv.bean.CsvBindByName;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Food {

    @CsvBindByName
    private String name;

    @CsvBindByName(column = "food group")
    private FoodGroup foodGroup;

    @CsvBindByName
    private int quantity;

    @CsvBindByName
    private int calories;

    @CsvBindByName
    private int protein;

    @CsvBindByName
    private int fat;

    @CsvBindByName
    private int carbs;

    @CsvBindByName(column = "calorie total")
    private int calorieTotal;
}
