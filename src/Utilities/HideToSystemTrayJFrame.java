package Utilities;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.UIManager;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

/**
 *
 * @author Mohammad Faisal
 * ermohammadfaisal.blogspot.com
 * facebook.com/m.faisal6621
 *
 */

public class HideToSystemTrayJFrame extends JFrame{
    TrayIcon trayIcon;
    SystemTray tray;
    Image image;
    public HideToSystemTrayJFrame(String title) throws MalformedURLException {
        super(title);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ignored){
        }
        if(SystemTray.isSupported()){
            tray=SystemTray.getSystemTray();
            //TODO: Fix image bug when export to jar
//            image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Assets/Auto.png"));
            image = Toolkit.getDefaultToolkit().getImage("../Assets/Auto.png");
            if(image == null){
                System.out.println("Image not found");
            }
            else {
                System.out.println("Image found");
            }
            ActionListener exitListener=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting....");
                    System.exit(0);
                }
            };
            PopupMenu popup=new PopupMenu();
            MenuItem defaultItem=new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem=new MenuItem("Open");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);
            trayIcon=new TrayIcon(image, "Automator", popup);
            trayIcon.setImageAutoSize(true);
        }else{
            System.out.println("system tray not supported");
        }
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState()==ICONIFIED){
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
                        System.out.println("unable to add to tray");
                    }
                }
                if(e.getNewState()==7){
                    try{
                        tray.add(trayIcon);
                        setVisible(false);
                        System.out.println("added to SystemTray");
                    }catch(AWTException ex){
                        System.out.println("unable to add to system tray");
                    }
                }
                if(e.getNewState()==MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    setVisible(true);
                    System.out.println("Tray icon removed");
                }
                if(e.getNewState()==NORMAL){
                    tray.remove(trayIcon);
                    setVisible(true);
                    System.out.println("Tray icon removed");
                }
            }
        });
        setIconImage(image);

        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    public void maximize(){
        this.setVisible(true);
    }

}