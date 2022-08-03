import Conditions.ScheduledAutomationPanel;
import Utilities.HideToSystemTrayJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

public class Automator {
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JButton timeBaseAutomationButton;
    private JButton batteryBasedAutomationButton;

    JFrame scheduleBasedFrame;
    ScheduledAutomationPanel scheduledAutomationPanel = new ScheduledAutomationPanel();

    private int ruleRowIndex = 0;


    Automator(){

        timeBaseAutomationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            if(scheduleBasedFrame == null){
                scheduleBasedFrame =  new JFrame("time-based automation");
                scheduleBasedFrame.setMinimumSize(new Dimension(700,240));
                scheduleBasedFrame.setContentPane(scheduledAutomationPanel);
                scheduleBasedFrame.setLocationRelativeTo(null);
                scheduleBasedFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                scheduleBasedFrame.setVisible(true);
            }
            else {
                scheduleBasedFrame.setVisible(true);
            }


            }
        });
    }


    public void hideSubWindows(){
        try{
            scheduleBasedFrame.setVisible(false);
        }
        catch (Exception ignored){

        }
    }
    public void showSubWindows(){
        scheduleBasedFrame.setVisible(true);
    }





    public static void main(String[] args) throws MalformedURLException {
        Automator automator = new Automator();
        HideToSystemTrayJFrame mainFrame = new HideToSystemTrayJFrame("Automator");
        mainFrame.setContentPane(automator.mainPanel);
        mainFrame.setMinimumSize(new Dimension(240,400));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Image icon ;
        icon = (Toolkit.getDefaultToolkit().getImage(
                mainFrame.getClass().getResource("/Assets/automator.ico")));

        mainFrame.setIconImage(icon);

        mainFrame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED){
                   automator.hideSubWindows();
                }

                else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
                    automator.showSubWindows();
                }
            }
        });


    }



}
