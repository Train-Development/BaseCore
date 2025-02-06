package com.blonicx.basecore.api.utils.io;

public class System {
    private static Runtime runtime = Runtime.getRuntime();

    public static String osName() {
        return java.lang.System.getProperty("os.name");
    }

    public static int availableProcessors() {
        return runtime.availableProcessors();
    }
}
