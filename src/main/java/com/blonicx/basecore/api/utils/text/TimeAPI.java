package com.blonicx.basecore.api.utils.text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAPI {
    public static String getFormattedTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
