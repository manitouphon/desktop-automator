package Task.Scripts;

import Task.Software.Software;
import Utilities.OSValidator;

import java.io.IOException;

public class ScriptExecutionHandler implements Runnable{


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
