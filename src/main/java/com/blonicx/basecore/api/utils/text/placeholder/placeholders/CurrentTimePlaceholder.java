package com.blonicx.basecore.api.utils.text.placeholder.placeholders;

import com.blonicx.basecore.api.utils.text.placeholder.Placeholder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTimePlaceholder implements Placeholder {

    @Override
    public String getValue() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
