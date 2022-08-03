package Task.Scripts;

import java.io.File;
import java.io.IOException;

public class Script {

    public static void executeScriptUNIX(String filePath) throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "./ " + filePath

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        Process p = pb.start();
    }
    public   static void executeSoftwareWindows(String filePath){

    }
}
