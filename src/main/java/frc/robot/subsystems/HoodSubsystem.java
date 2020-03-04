package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AbsoluteEncoder;
import frc.robot.Constants.HoodConstants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class HoodSubsystem extends SubsystemBase {
    // Constants
    private static final int motorPort = HoodConstants.motorPort;
    private static final int encoderPort = HoodConstants.encoderPort;

    // Hardware
    private VictorSP motor;
    private AbsoluteEncoder encoder;

    // Other

    /**
     * Basic constructor
     */
    public HoodSubsystem() {
        motor = new VictorSP(motorPort);
        encoder = new AbsoluteEncoder(new AnalogPotentiometer(encoderPort));
    }

    /**
     * Retruns angle
     * 
     * @return angle
     */
    public double getAngle() {
        return encoder.getAngle();
    }

    /**
     * Hardware feedback
     */
    public void debug() {
        SmartDashboard.putNumber("Angle", encoder.getAngle());
    }

    /**
     * Turn hood
     * 
     * @param input
     */
    public void turn(double input) {
        motor.set(input); // TODO tune
    }
}