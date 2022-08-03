package Task.Scripts;

import Conditions.DateWithCheckBox;
import Task.Power.PowerStateActionChangeHandler;
import Task.Software.SoftwareExecutionHandler;
import Utilities.EasyGridBagConstrain;
import Utilities.OSValidator;
import Utilities.PanelType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ScriptsExecutionPanel extends JPanel{
    JPanel thisClass = this;

    SoftwareExecutionHandler scriptExecutionHandler = new SoftwareExecutionHandler();

    JLabel _from = new JLabel(" from ");

    DateWithCheckBox softwareDateWithCheckBox;
    Icon icon = new ImageIcon(this.getClass().getResource("/Assets/Auto.png"));
    JButton softwareChooserButton = new JButton(icon);
    JFileChooser fileChooser = new JFileChooser();
    File targetFile;
    String filePath;

    JLabel fileName;

    SoftwareExecutionHandler task = new SoftwareExecutionHandler();

    public ScriptsExecutionPanel(String parentPanelType){
        super(new GridBagLayout());
        if(parentPanelType.equals(PanelType.getTimeType())) {
            initializeTimeType();
        }
        else if(parentPanelType.equals(PanelType.getBatteryType())){
            initializeBatteryType();
        }
    }


    public void initializeFileChooser(){
        softwareDateWithCheckBox = new DateWithCheckBox("Execute Script", scriptExecutionHandler);
        if(OSValidator.isMac()) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("Shell script file (.sh)", "sh"));
            fileChooser.setCurrentDirectory(new File("/"));
        }
        else if(OSValidator.isWindows()){
            fileChooser.setFileFilter(new FileNameExtensionFilter("Batch file (.bat)", "bat"));
        }
    }
    private void initializeTimeType(){
        initializeFileChooser();

        softwareChooserButton.setText("Choose File...");

        fileName = new JLabel();
        fileName.setForeground(new Color(36, 186, 89, 255));


        this.add(softwareDateWithCheckBox.getPanel(), EasyGridBagConstrain.getGBConstraints(0.5, 0.5, 0, 0));
        this.add(_from, EasyGridBagConstrain.getGBConstraints(0.1, 0.1, 1, 0));
        this.add(fileName, EasyGridBagConstrain.getGBConstraints(0.5,0.5,2,0));
        this.add(softwareChooserButton,EasyGridBagConstrain.getGBConstraints(0.1,0.1,3,0));


        softwareChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(thisClass);

                if(returnValue == JFileChooser.APPROVE_OPTION){
                    targetFile = fileChooser.getSelectedFile();
                    try {
                        filePath = targetFile.getCanonicalPath();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.out.println("Load file: OK");
                }
                else {
                    System.out.println("Load file: FAIL");
                }

                System.out.println(filePath);

                task.setTargetFile(filePath);
                softwareDateWithCheckBox.setTask(task);
                fileName.setText(getSoftwareName(filePath));

            }
        });


        softwareDateWithCheckBox.setDEBUG_MODE(true);
    }
    private void initializeBatteryType(){

    }




    private String getSoftwareName(String filePath){
        StringBuilder fileName = new StringBuilder();
        for(int i = filePath.length() - 1; i >= 0; i-- ){
            if(filePath.charAt(i) == '/' || filePath.charAt(i) == '\\'){
                fileName.reverse();
                break;
            }
            else {
                fileName.append(filePath.charAt(i));

            }
        }
        System.out.println(fileName);
        return String.valueOf(fileName);
    }

}
