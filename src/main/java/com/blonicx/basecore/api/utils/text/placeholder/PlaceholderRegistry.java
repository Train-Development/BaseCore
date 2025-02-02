package com.blonicx.basecore.api.utils.text.placeholder;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class PlaceholderRegistry {

    private static final Map<String, Placeholder> placeholders = new HashMap<>();

    // This method will be called in the setupPlaceholders function
    public static void loadPlaceholdersFromDirectory(String directoryPath) {
        File folder = new File(directoryPath);

        // Check if the directory exists
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Directory does not exist or is not a valid directory.");
            return;
        }

        // Loop through each file in the directory
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                try {
                    // Load the class using reflection
                    Class<?> clazz = Class.forName(className);

                    // Check if the class implements Placeholder interface
                    if (Placeholder.class.isAssignableFrom(clazz)) {
                        // Instantiate the class and register it
                        Constructor<?> constructor = clazz.getConstructor();
                        Placeholder placeholderInstance = (Placeholder) constructor.newInstance();

                        // Register placeholder
                        String placeholderName = className.toLowerCase(); // Use class name as placeholder name
                        placeholders.put(placeholderName, placeholderInstance);
                        System.out.println("Loaded placeholder: " + placeholderName);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to load placeholder class " + className + ": " + e.getMessage());
                }
            }
        }
    }

    public static String processString(String input) {
        for (Map.Entry<String, Placeholder> entry : placeholders.entrySet()) {
            input = input.replace("%" + entry.getKey() + "%", entry.getValue().getValue());
        }
        return input;
    }
}