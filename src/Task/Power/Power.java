package Task.Power;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public  class Power {
    public static void shutdown_MAC() throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "osascript -e 'tell app \"System Events\" to shut down'"

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        StringBuffer output = new StringBuffer();
        Process p = pb.start();
    }
    public static void restart_MAC() throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "osascript -e 'tell app \"System Events\" to restart'"

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        StringBuffer output = new StringBuffer();
        Process p = pb.start();
    }
    public static void shutdown_UNIX() throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "shutdown now"

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        StringBuffer output = new StringBuffer();
        Process p = pb.start();
    }
    public static void restart_UNIX() throws IOException {
        String[] commands = {
                "/bin/sh",
                "-c",
                "reboot now"

        };

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/usr/bin"));
        StringBuffer output = new StringBuffer();
        Process p = pb.start();
    }

    public static void shutdown_WIN(){

    }
    public static void restart_WIN(){

    }

}
