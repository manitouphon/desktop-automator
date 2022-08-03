package Conditions;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class BatteryBasedAutomationPanel {


    //Thread calling and comparing.
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(getMacBattery());
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    public static String getMacBattery(){
        String[] commands = {
                "/bin/sh",
                "-c",
                "pmset -g batt | grep -Eo \"\\d+%\" | cut -d% -f1"
                //pmset -g batt : returns string of info battery. Use the grep tool in UNIX with the Regex above to
                //get the Battery as Integer.

        };
        try
        {
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File("/usr/bin"));
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine())!= null)
            {
                output.append(line + "\n");
            }
            return String.valueOf(output);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String getWindowBattery(){
        return null;
    }







}
