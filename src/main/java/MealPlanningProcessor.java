import model.Food;
import model.Meal;
import service.MealConstructor;
import util.ResourceUtil;
import java.util.List;

public class MealPlanningProcessor {

    static void main(String[] args){
        final List<Food> foods = ResourceUtil.getFoodsFromFileReader(ResourceUtil.getFileReaderFromInputFolder());
        final List<Meal> meals = MealConstructor.constructMeals(foods);
    }
}
