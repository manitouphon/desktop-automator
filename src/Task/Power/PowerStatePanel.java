package Task.Power;

import Conditions.DateWithCheckBox;
import Utilities.EasyGridBagConstrain;
import Utilities.PanelType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerStatePanel extends JPanel {

    PowerStateActionChangeHandler powerStateRunnable = new PowerStateActionChangeHandler();
    JComboBox powerStates;
    String[] powerModes = {"Shut down", "Restart"};
    JLabel _to = new JLabel(" to ");

    DateWithCheckBox systemPowerDate;


    public PowerStatePanel(String parentPanelType){
        super(new GridBagLayout());
        if(parentPanelType.equals(PanelType.getTimeType())) {
            initializeTimeType();
        }
        else if(parentPanelType.equals(PanelType.getBatteryType())){
            initializeBatteryType();
        }
    }

    private void initializeTimeType(){
        //Power State components Config
        powerStates = new JComboBox();
        for (int i = 0; i < powerModes.length; i++) {
            powerStates.addItem(powerModes[i]);
        }
        powerStates.setSelectedItem(null);


        systemPowerDate = new DateWithCheckBox("Power State", powerStateRunnable);
        this.add(systemPowerDate.getPanel(), EasyGridBagConstrain.getGBConstraints(0.5, 0.5, 0, 1));
        this.add(_to, EasyGridBagConstrain.getGBConstraints(0.1, 0.1, 1, 1));
        this.add(powerStates, EasyGridBagConstrain.getGBConstraints(0.2, 2.0, 2, 1));
        systemPowerDate.setDEBUG_MODE(true);

        //Power State Action Listener
        powerStates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PowerState.SelectedItem: " + powerStates.getSelectedItem());
                powerStateRunnable = new PowerStateActionChangeHandler();
                if (powerStates.getSelectedItem() == powerModes[0]) {
                    powerStateRunnable.setPowerStateAction(1);
                    System.out.println("Setting to shutdown");
                } else if (powerStates.getSelectedItem() == powerModes[1]) {
                    powerStateRunnable.setPowerStateAction(2);
                    System.out.println("Setting to restart");
                } else {
                    powerStateRunnable.setPowerStateAction(0);
                }
                systemPowerDate.setTask(powerStateRunnable);
            }
        });
    }

    private void initializeBatteryType(){
        //TODO: Make battery Type panel
    }
}
