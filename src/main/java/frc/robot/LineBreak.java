package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class LineBreak {
    DigitalInput lineOut;
    DigitalInput lineIn;

    public LineBreak(int lineSensor, int lineSource) {
        lineIn = new DigitalInput(lineSensor);
        lineOut = new DigitalInput(lineSource);
    }

    public boolean get() {
        return lineIn.get();
    }
}