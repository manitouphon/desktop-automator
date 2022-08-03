package Conditions;

import Task.Power.PowerStatePanel;
import Task.Scripts.ScriptsExecutionPanel;
import Task.Software.SoftwareExecutionPanel;
import Task.Volume.VolumePanel;
import Utilities.EasyGridBagConstrain;
import Utilities.PanelType;

import javax.swing.*;
import java.awt.*;



public class ScheduledAutomationPanel extends JScrollPane {
//    JScrollPane scroll = new JScrollPane();
    JPanel container = new JPanel(new GridBagLayout());
    String type = PanelType.getTimeType();

    public ScheduledAutomationPanel(){
        super();



        VolumePanel volumePanel = new VolumePanel(type);
        PowerStatePanel powerStatePanel = new PowerStatePanel(type);
        SoftwareExecutionPanel softwareExecutionPanel = new SoftwareExecutionPanel(type);
        ScriptsExecutionPanel scriptsExecutionPanel = new ScriptsExecutionPanel(type);

        container.add(volumePanel, EasyGridBagConstrain.getGBConstraints(1.0,1.0,0,0));
        container.add(powerStatePanel, EasyGridBagConstrain.getGBConstraints(1.0,1.0,0,1));
        container.add(softwareExecutionPanel, EasyGridBagConstrain.getGBConstraints(1.0,1.0,0,2));
        container.add(scriptsExecutionPanel, EasyGridBagConstrain.getGBConstraints(1.0,1.0,0,3));

        this.setViewportView(container);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        container.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth() , (int)this.getPreferredSize().getHeight() + 300));



    }



}
