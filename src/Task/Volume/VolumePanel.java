package Task.Volume;

import Conditions.DateWithCheckBox;
import Utilities.EasyGridBagConstrain;
import Utilities.MinMaxNumberFormat;
import Utilities.PanelType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumePanel extends JPanel{

    AudioVolumeChangeHandler volumeRunnable = new AudioVolumeChangeHandler();
    DateWithCheckBox systemVolumeDate;
    MinMaxNumberFormat volNumFormat = new MinMaxNumberFormat(0,100);
    JSpinner volumeState ;

    JLabel _to = new JLabel(" to ");
    JLabel _percentage = new JLabel(" % ");
    float currentVol;


    public VolumePanel(String parentPanelType){
        super(new GridBagLayout());
        if(parentPanelType.equals(PanelType.getTimeType())){
            initializeTimeType();
        }
        else if(parentPanelType.equals(PanelType.getBatteryType())){
            initializeBatteryType();
        }

    }

    private void initializeTimeType(){

        //Volume State Components config
        if(System.getProperty("os.name").contains("Mac")){
            currentVol = (Audio.getOutputVolumeForMac());
        }else if(System.getProperty("os.name").contains("Windows")) {
            currentVol = Audio.getMasterOutputVolume();
        }
        else currentVol = 0.0f;
        volumeRunnable.setVolume(currentVol);
        _to.setHorizontalAlignment(SwingConstants.CENTER);

        volumeState = new JSpinner(new SpinnerNumberModel(((int)(currentVol * 100.0f)),0,100,1));

        systemVolumeDate = new DateWithCheckBox("change volume", volumeRunnable);
        this.add(systemVolumeDate.getPanel(), EasyGridBagConstrain.getGBConstraints(0.5, 0.5, 0, 0));
        this.add(_to,EasyGridBagConstrain.getGBConstraints(0.1, 0.1, 1, 0));
        this.add(volumeState,EasyGridBagConstrain.getGBConstraints(0.2, 2.0, 2, 0));
        this.add(_percentage,EasyGridBagConstrain.getGBConstraints(0.2, 2.0, 3, 0));


        systemVolumeDate.setDEBUG_MODE(true);

        //Volume State Action Listener
        volumeState.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentVol = Float.parseFloat(String.valueOf(volumeState.getValue()) ) / 100.0f;
                volumeRunnable = new AudioVolumeChangeHandler();
                volumeRunnable.setVolume(currentVol);
                systemVolumeDate.setTask(volumeRunnable);
                System.out.println("Changed " + currentVol);

            }
        });
    }

    private void initializeBatteryType(){
        //TODO: Make battery panel
    }

}
