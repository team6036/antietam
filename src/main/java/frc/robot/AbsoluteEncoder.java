package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * Add your docs here.
 */
public class AbsoluteEncoder {
    private double offset = 0.0;
    private AnalogPotentiometer pot;

    public AbsoluteEncoder(AnalogPotentiometer pot) {
        this.pot = pot;
    }

    public double getAngle() {
        return this.pot.get() + offset;
        // return this.pot.get();
    }

    public void reset() {
        offset = -1 * pot.get();
    }
}