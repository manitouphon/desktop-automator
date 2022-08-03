package Task.Software;

import java.io.File;
import java.io.IOException;

public class Software {

    public static void executeSoftwareMac(String filePath) throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "open " + filePath

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        Process p = pb.start();
    }
    public   static void executeSoftwareWindows(String filePath){

    }
}
