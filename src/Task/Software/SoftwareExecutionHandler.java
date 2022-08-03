package Task.Software;

import Utilities.OSValidator;

import java.io.File;
import java.io.IOException;

public class SoftwareExecutionHandler implements Runnable {
    String filePath;


    @Override
    public void run() {
        if(OSValidator.isMac()){
            try {
                Software.executeSoftwareMac(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(OSValidator.isWindows()){

        }
    }



    public void setTargetFile(String filePath){
        this.filePath = filePath;
    }
}
