package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class HoodSubsystem extends SubsystemBase {
    private static final int motorPort = HoodConstants.motorPort;
    private static DutyCycleEncoder dCEncoder = new DutyCycleEncoder(0);

    // Hardware
    private VictorSP motor;
    private Encoder encoder = new Encoder(1, 2);
    // private AbsoluteEncoder encoder;

    // Other

    /**
     * Basic constructor
     */
    public HoodSubsystem() {
        motor = new VictorSP(motorPort);
        // encoder = new AbsoluteEncoder(new AnalogPotentiometer(encoderPort));
        // motor.setInverted(true);
    }

    /**
     * Retruns angle
     * 
     * @return angle
     */
    public double getAngle() {
        return dCEncoder.getDistance();
    }

    /**
     * Hardware feedback
     */
    public void debug() {
        SmartDashboard.putNumber("Angle", getAngle());
        SmartDashboard.putNumber("Hood optical", getOpticalEncoder());
        SmartDashboard.putNumber("Hood dce", getAngle());
    }

    /**
     * Turn hood
     * 
     * @param input
     */
    public void turn(double input) {
        motor.set(input); // TODO tune
    }

    public double getOpticalEncoder() {
        return encoder.getDistance();
    }

}