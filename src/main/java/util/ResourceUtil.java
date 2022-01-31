package util;

import com.opencsv.bean.CsvToBeanBuilder;
import constants.MealPlanningConstants;
import model.Food;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class ResourceUtil {

    public static FileReader getFileReaderFromInputFolder() {
        try {
            final File currentDirectory = new File(ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (currentDirectory.exists()) {
                final File inputDirectory = new File(currentDirectory.getAbsolutePath() + "/input");
                if (inputDirectory.exists()) {
                    if (inputDirectory.listFiles() != null) {
                        List<File> files = Arrays.asList(inputDirectory.listFiles());
                        int index = 0;
                        for (final File file : files) {
                            if (files.get(index).getName().equals(MealPlanningConstants.FOODS_CSV)) {
                                return new FileReader(file);
                            }
                            index++;
                        }
                    }
                }
            }
        } catch (URISyntaxException uriSyntaxException) {
            System.out.println("uriSyntaxException");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fileNotFoundException");
        }
        return null;
    }

    public static List<Food> getFoodsFromFileReader(final FileReader fileReader) {
        List<Food> foods = new CsvToBeanBuilder(fileReader)
                .withType(Food.class)
                .build()
                .parse();

        try {
            fileReader.close();
        } catch (final IOException ioException) {
            System.out.println("ioException");
        }
        return foods;
    }
}
