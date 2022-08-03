package Utilities;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class MinMaxNumberFormat{
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter;

    public MinMaxNumberFormat(int minNum, int maxNum){
        format.setGroupingUsed(false);
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(minNum);
        formatter.setMaximum(maxNum);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(false);
    }


    public NumberFormatter getFormatter() {
        return formatter;
    }
}