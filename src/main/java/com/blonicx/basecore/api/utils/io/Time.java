package com.blonicx.basecore.api.utils.io;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    // Get current Time in format "yyyy-MM-dd HH:mm:ss" // Example Output: 2025-02-23 16:19:00
    public static String getFormattedTime(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }
}
