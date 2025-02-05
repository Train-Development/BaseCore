package com.blonicx.basecore.api.utils.io;

public class SystemAPI {
    private static Runtime runtime = Runtime.getRuntime();

    public static String osName() {
        return System.getProperty("os.name");
    }

    public static int availableProcessors() {
        return runtime.availableProcessors();
    }
}
