package net.redpandasec.labs.Classes;

import java.io.IOException;

public class Utils {

    public static void exec(String cmd) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(cmd);
    }
}
