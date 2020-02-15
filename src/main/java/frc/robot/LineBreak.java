package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.LinebreakConstants;
public class LineBreak{
    private static int inputPort = LinebreakConstants.inputPort;
    private static DigitalInput input = new DigitalInput(inputPort);
    public static boolean get(){
        return input.get();
    }
}