package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.LinebreakConstants;
public class LineBreak{
    private static int inputPort = LinebreakConstants.inputPort;
    private static int sourcePort = LinebreakConstants.sourcePort;
    private static DigitalInput source = new DigitalInput(sourcePort);
    private static DigitalInput input = new DigitalInput(inputPort);
    public static boolean get(){
        return input.get();
    }
}