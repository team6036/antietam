package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AbsoluteEncoder;
import frc.robot.Constants.HoodConstants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class HoodSubsystem   extends SubsystemBase {
    private static final int motorPort = HoodConstants.motorPort;
    private static final int encoderPort = HoodConstants.encoderPort;
    private static DutyCycleEncoder dCEncoder = new DutyCycleEncoder(0);

    private VictorSP motor;
    private Encoder encoder = new Encoder(1, 2);
    //private AbsoluteEncoder encoder;

    public HoodSubsystem() {
        motor = new VictorSP(motorPort);
        //encoder = new AbsoluteEncoder(new AnalogPotentiometer(encoderPort));
        //motor.setInverted(true);
    }
    public double getAngle(){
        return dCEncoder.getDistance();
    }
    
    public void turn(double input) {
        motor.set(input); //TODO tune
    }

    public double getOpticalEncoder(){
        return encoder.getDistance();
    }

    
}