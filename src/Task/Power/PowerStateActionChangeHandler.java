package Task.Power;

import Utilities.OSValidator;

import java.io.IOException;

public class PowerStateActionChangeHandler implements Runnable{

    final String shutdown = "Shut down";
    final String restart = "Restart";

    String powerState = "DEFAULT";


    @Override
    public void run() {
       if(OSValidator.isMac()){
           forMAC();
       }
       else if(OSValidator.isWindows()){
           forWIN();
       }
       else if(OSValidator.isUnix()){
           forUNIX();
       }
    }


    private void forMAC(){
        System.out.println(powerState);
        if(this.powerState == shutdown){
            System.out.println("Shutting down");
            try {
                Power.shutdown_MAC();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(this.powerState == restart){
            System.out.println("Restarting");
            try {
                Power.restart_MAC();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Wrong powerState assignment. Doing Nothing");
            //Do nothing
        }
    }
    private void forWIN(){
        System.out.println(powerState);
        if(this.powerState == shutdown){
            Power.shutdown_WIN();
        }
        else if(this.powerState == restart){
            Power.restart_WIN();
        }
        else {
            System.out.println("Wrong powerState assignment. Doing Nothing");
            //Do nothing
        }
    }
    private void forUNIX(){
        System.out.println(powerState);
        if(this.powerState == shutdown){
            System.out.println("Shutting down");
            try {
                Power.shutdown_UNIX();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(this.powerState == restart){
            System.out.println("Restarting");
            try {
                Power.restart_UNIX();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Wrong powerState assignment. Doing Nothing");
            //Do nothing
        }
    }


    /**
     * PowerStateCodes:
     * 1 Shutdown
     * 2 Restart;
     * @param powerStateCode
     */

    public void setPowerStateAction(int powerStateCode ){

        if(powerStateCode == 1){
            this.powerState = shutdown;
            System.out.println("Settiing Power State: "+powerState);
        }
        else if(powerStateCode == 2){
            this.powerState = restart;
            System.out.println("Settiing Power State: " + powerState);
        }
        else {
            this.powerState = "NOTHING";
            System.out.println("Wrong Power State Code");
        }
    }
}
