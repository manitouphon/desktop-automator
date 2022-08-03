package Task.Volume;

public class AudioVolumeChangeHandler implements Runnable{

    float volume;


    public AudioVolumeChangeHandler(){

    }
    @Override
    public void run() {
        if(System.getProperty("os.name").contains("Mac")){
            Audio.setOutputVolumeForMac(volume);
        }
        else if(System.getProperty("os.name").contains("Windows")){
            Audio.setMasterOutputVolume(volume);
        }
        System.out.println("Volume: " + volume);
    }

    public void setVolume(float vol){
        volume = vol;
    }


}
