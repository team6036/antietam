package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AbsoluteEncoder;
import frc.robot.Constants.HoodConstants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class HoodSubsystem extends SubsystemBase {
    private static final int motorPort = HoodConstants.motorPort;
    private static final int encoderPort = HoodConstants.encoderPort;

    private VictorSP motor;
    private AbsoluteEncoder encoder;

    public HoodSubsystem() {
        motor = new VictorSP(motorPort);
        encoder = new AbsoluteEncoder(new AnalogPotentiometer(encoderPort));
    }
    public double getAngle(){
        return encoder.getAngle();
    }
    
    public void turn(double input) {
        motor.set(input*0.00001); //TODO tune
    }
}