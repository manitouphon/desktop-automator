package Utilities;

import java.awt.*;

public class EasyGridBagConstrain{

    public static GridBagConstraints getGBConstraints(double weightX, double weightY, int gridX, int gridY ){
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = weightX;
        c.weighty = weightY;
        c.gridx = gridX;
        c.gridy = gridY;
        c.fill = GridBagConstraints.HORIZONTAL;


        return c;
    }

}
