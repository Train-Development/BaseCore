package com.blonicx.basecore.api.utils.io;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAPI {
    public static String getFormattedTime(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }
}
