package Conditions;

import Utilities.EasyGridBagConstrain;
import Utilities.MinMaxNumberFormat;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DateWithCheckBox{
    boolean DEBUG_MODE = false;
    JButton DEBUG_BUTTON = new JButton("Trigger Now!");
    private void DEBUG_DO_TASK(){
        this.task.run();
    }

    JPanel panel = new JPanel(new GridBagLayout());
    Runnable task;

    Date now;
    Date triggeredDate;

    java.util.Timer timer;


    int hour,minute;

    JLabel _at = new JLabel("At  ");
    JLabel _time = new JLabel("   Time:");
    JLabel _colon = new JLabel(":");

    JCheckBox activatedCheckBox;
    JCheckBox dailyRepeatCheckBox;

    JFormattedTextField hourInput;
    JFormattedTextField minuteInput;
    JDateChooser dateChooser;

    MinMaxNumberFormat dateValidator = new MinMaxNumberFormat(1,31);
    MinMaxNumberFormat monthValidator = new MinMaxNumberFormat(1,12);
    MinMaxNumberFormat yearValidator =  new MinMaxNumberFormat(2021,6000);
    MinMaxNumberFormat hourValidator = new MinMaxNumberFormat(0,24);
    MinMaxNumberFormat minuteValidator = new MinMaxNumberFormat(0,60) ;

    public JPanel getPanel() {
        return panel;
    }

    public void setDEBUG_MODE(boolean debug_mode){
        this.DEBUG_MODE = debug_mode;
        DEBUG_BUTTON.setVisible(debug_mode);
    }

    private void scheduleFutureTask(boolean isChecked){
        if(isChecked) {
            if(!dailyRepeatCheckBox.isSelected()){
                updateDurationTime();
                timer = new java.util.Timer(false);
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        task.run();
                        timer.cancel();
                    }
                }, triggeredDate, 1);
            }//if doTask NOT daily
            else{
                updateDurationTime();
                timer = new Timer(false);
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        task.run();
                    }
                }, triggeredDate, 86400000);
            }//If doTask Daily
        }
    }

    private void updateDurationTime(){
        this.now = new Date(System.currentTimeMillis());
        long duration ;

        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();

        now.setTime(this.now);
        now.set(Calendar.SECOND,0);

        target.setTime(now.getTime());

        if(!dailyRepeatCheckBox.isSelected()) {
            target.setTime(dateChooser.getDate());
            target.set(Calendar.HOUR_OF_DAY, hour);
            target.set(Calendar.MINUTE, minute);

        }else {

            target.set(Calendar.HOUR_OF_DAY, hour);
            target.set(Calendar.MINUTE, minute);
            triggeredDate = target.getTime();
            if(triggeredDate.getTime() - now.getTime().getTime() <= 0 ){
                target.set(Calendar.DATE, (target.get((Calendar.DATE)) + 1 )); //If time expires, schedule new duration for tomorrow date.
            }

        }
        System.out.println(target.getTime());
        triggeredDate = target.getTime();






    }




    public DateWithCheckBox(String operation, Runnable task){
        this.now = new Date(System.currentTimeMillis());
        Calendar now = Calendar.getInstance();
        now.setTime(this.now);
        this.task = task;



        hourInput = new JFormattedTextField(hourValidator.getFormatter());
        minuteInput = new JFormattedTextField(minuteValidator.getFormatter());
        activatedCheckBox = new JCheckBox();
        dailyRepeatCheckBox = new JCheckBox();
        dateChooser = new JDateChooser();
        dateChooser.setDate(now.getTime());
        dateChooser.getDateEditor().setEnabled(false);





        activatedCheckBox.setText(operation + "   ");
        dailyRepeatCheckBox.setText("Repeat Daily");

        _at.setHorizontalAlignment(SwingConstants.LEFT);
        _time.setHorizontalAlignment(SwingConstants.RIGHT);
        _colon.setHorizontalAlignment(SwingConstants.CENTER);



        hour =now.get(Calendar.HOUR_OF_DAY);
        minute =now.get(Calendar.MINUTE);
        hourInput.setText(String.valueOf(hour));
        minuteInput.setText(String.valueOf(minute));







        panel.add(activatedCheckBox, EasyGridBagConstrain.getGBConstraints(0.1, 0.1, 0, 0));
        panel.add(_at, EasyGridBagConstrain.getGBConstraints(0.1, 0.1, 1, 0));
        panel.add(dateChooser, EasyGridBagConstrain.getGBConstraints(0.8, 0.4, 2, 0));
        panel.add(_time, EasyGridBagConstrain.getGBConstraints(0.2, 0.2, 3, 0));
        panel.add(hourInput, EasyGridBagConstrain.getGBConstraints(0.4, 0.4, 4, 0));
        panel.add(_colon, EasyGridBagConstrain.getGBConstraints(0.0, 0.0, 5, 0));
        panel.add(minuteInput, EasyGridBagConstrain.getGBConstraints(0.4, 0.4, 6, 0));
        panel.add(dailyRepeatCheckBox,EasyGridBagConstrain.getGBConstraints(1.0, 0.2, 0, 1));
        panel.add(DEBUG_BUTTON, EasyGridBagConstrain.getGBConstraints(0.1,0.1,1,1));

        DEBUG_BUTTON.setVisible(DEBUG_MODE);










        DEBUG_BUTTON.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DEBUG_DO_TASK();
            }
        });



        activatedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(activatedCheckBox.isSelected() || timer == null){
                    scheduleFutureTask(activatedCheckBox.isSelected());
                }
                else {
                    if(timer != null ){
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }
                }
                toggleState(activatedCheckBox.isSelected());
            }
        });

        dailyRepeatCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateChooser.setVisible(!dailyRepeatCheckBox.isSelected());
            }
        });


        hourInput.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    hour = Integer.parseInt(hourInput.getText(), 10);
                }
                catch (Exception exception){}
            }
        });
        minuteInput.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    minute = Integer.parseInt(minuteInput.getText(), 10);
                }
                catch (Exception exception){}
            }
        });
    }

    private void toggleState(boolean checkBoxIsChecked){
        dateChooser.setEnabled(!checkBoxIsChecked);
        hourInput.setEditable(!checkBoxIsChecked);
        minuteInput.setEditable(!checkBoxIsChecked);
        dailyRepeatCheckBox.setEnabled(!checkBoxIsChecked);
    }
    public void setTask(Runnable newTask){
        this.task = null;
        this.task = newTask;
    }


    private void updateDate(){

    }






};
