package com.blonicx.basecore.api.utils.placeholder;

import java.util.HashMap;
import java.util.Map;

public class PlaceholderRegistry {

    // Store placeholder -> replacement value mappings
    private static final Map<String, Placeholder> placeholders = new HashMap<>();

    // Register a placeholder with its value supplier (could be a dynamic value)
    public static void registerPlaceholder(String placeholder, Placeholder valueSupplier) {
        placeholders.put(placeholder, valueSupplier);
    }

    public static String processString(String input) {
        for (Map.Entry<String, Placeholder> entry : placeholders.entrySet()) {
            input = input.replace("%" + entry.getKey() + "%", entry.getValue().getValue());
        }
        return input;
    }
}