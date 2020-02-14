package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.LinebreakConstants;
class LineBreak{
    private int inputPort = LinebreakConstants.inputPort;
    private DigitalInput input;
    LineBreak(){
        input = new DigitalInput(inputPort);
    }
    public boolean get(){
        return input.get();
    }
}