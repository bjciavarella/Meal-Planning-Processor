package com.pinetreesoftware.util;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;
import com.pinetreesoftware.constants.MealPlanningConstants;
import com.pinetreesoftware.model.Days;
import com.pinetreesoftware.model.Food;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceUtil {

    public static FileReader getFileReaderFromInputFolder(final String fileName) {
        try {
            final File inputDirectory = new File( getLocalDirectory() + "\\input");
            if (inputDirectory.exists()) {
                if (inputDirectory.listFiles() != null) {
                    List<File> files = Arrays.asList(inputDirectory.listFiles());
                    int index = 0;
                    for (final File file : files) {
                        if (files.get(index).getName().equals(fileName)) {
                            return new FileReader(file);
                        }
                        index++;
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fileNotFoundException - " + fileNotFoundException.getMessage());
        }
        return null;
    }

    private static String getLocalDirectory() {
        try {
            File currentDirectory =  new File(ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            final String currentDirectoryString = currentDirectory.getAbsolutePath();
            return currentDirectoryString.substring(0, currentDirectoryString.lastIndexOf("\\"));
        } catch (URISyntaxException uriSyntaxException) {
            System.out.println("uriSyntaxException - " + uriSyntaxException.getMessage());
        }
        return null;
    }

    public static void writeDaysToFile(final Gson gson, final Days days) {
        String currentDirectory = getLocalDirectory();
        if (currentDirectory != null) {
            String outputFilePath = currentDirectory + "\\" + MealPlanningConstants.DAYS_JSON;
            System.out.println("Writing to file: " + outputFilePath);
            try {
                FileWriter fileWriter = new FileWriter(outputFilePath);
                gson.toJson(days, fileWriter);
                fileWriter.close();
            } catch (IOException ioException) {
                System.out.println("IOException - " + ioException.getMessage());
            }
        }
    }

    public static List<Food> getFoodsFromFileReader(final FileReader fileReader) {
        List<Food> foodsList = new ArrayList<>();
        if (fileReader != null) {
            foodsList = (new CsvToBeanBuilder(fileReader)
                    .withType(Food.class)
                    .build()
                    .parse());

            try {
                fileReader.close();
            } catch (final IOException ioException) {
                System.out.println("ioException - " + ioException.getMessage());
            }
        } else {
            System.out.println("File Reader is null");
        }
        return foodsList;
    }
}
